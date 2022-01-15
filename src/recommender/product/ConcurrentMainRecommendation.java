package recommender.product;

import common.Product;
import common.ProductReview;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

public class ConcurrentMainRecommendation {

    public static void main(String[] args) {
        String user = args[0];
        Path file = Paths.get("data");
        try {
            Date start, end;
            start = new Date();

            ConcurrentLinkedDeque<Product> productList =
                    Files
                            .walk(file, FileVisitOption.FOLLOW_LINKS)
                            .parallel()
                            .filter(f -> f.toString().endsWith(".txt"))
                            .collect(ConcurrentLinkedDeque<Product>::new
                                    , new ConcurrentLoaderAccumulator(),
                                    ConcurrentLinkedDeque::addAll);

            Map<String, List<ProductReview>>
                    productsByBuyer = productList
                    .parallelStream()
                    .<ProductReview>flatMap(p ->
                            p.getReviews().stream().map(r -> new
                                    ProductReview(p, r.getUser(), r.getValue())))
                    .collect(Collectors.groupingByConcurrent(p ->
                            p.getBuyer()));

            Map<String, List<ProductReview>>
                    recommendedProducts = productsByBuyer.get(user)
                    .parallelStream()
                    .map(p -> p.getReviews())
                    .flatMap(Collection::stream)
                    .map(r -> r.getUser())
                    .distinct()
                    .map(key -> productsByBuyer.get(key))
                    .flatMap(Collection::stream)
                    .collect(Collectors.groupingByConcurrent(p ->
                            p.getTitle()));

            List<ProductRecommendation> recommendations =
                    recommendedProducts
                            .entrySet()
                            .parallelStream()
                            .map(entry -> new
                                    ProductRecommendation(entry.getKey(),
                                    entry.getValue().stream().mapToInt(p ->
                                            p.getValue()).average().getAsDouble()))
                            .sorted()
                            .collect(Collectors.toList());

            end = new Date();
            recommendations.forEach(pr -> System.out.println
                    (pr.getTitle() + ": " + pr.getValue()));
            System.out.println("Execution Time: " + (end.getTime() -
                    start.getTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
