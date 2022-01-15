package recommender;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedDeque;

// Chapter 8
// First example, first approach
public class ConcurrentMainBasicSearch {

//    receives a word as the input query and searches all the files that store the information
//    of the products whether that word is included in one of the fields that define the product, no matter which

    public static void main(String args[]) {
        String query = args[0];
        Path file = Paths.get("data");
        try {
            Date start, end;
            start = new Date();

            ConcurrentLinkedDeque<String> results = Files
                    .walk(file, FileVisitOption.FOLLOW_LINKS)
                    .parallel()
                    .filter(f -> f.toString().endsWith(".txt"))
                    .collect(ConcurrentLinkedDeque::new,
                            new ConcurrentStringAccumulator
                                    (query),
                            ConcurrentLinkedDeque::addAll);

            end = new Date();
            System.out.println("Results for Query: "+query);
            System.out.println("*************");
            results.forEach(System.out::println);
            System.out.println("Execution Time: "+(end.getTime()-
                    start.getTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
