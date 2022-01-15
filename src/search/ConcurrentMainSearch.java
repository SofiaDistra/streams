package recommender;

import common.Product;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedDeque;

// Chapter 8
// First example, second approach - advanced search
public class ConcurrentMainSearch {

    public static void main(String args[]) {
        String query = args[0];
        Path file = Paths.get("data");
        try {
            Date start, end;
            start=new Date();
            ConcurrentLinkedDeque<Product> results;
            results = Files
                    .walk(file, FileVisitOption.FOLLOW_LINKS)
                    .parallel()
                    .filter(f -> f.toString().endsWith(".txt"))
                    .collect(ConcurrentLinkedDeque<Product>::new,
                            new ConcurrentObjectAccumulator
                                    (query),
                            ConcurrentLinkedDeque::addAll);
            end=new Date();
            System.out.println("Results");
            System.out.println("*************");
            results.forEach(p ->
                    System.out.println(p.getTitle()));
            System.out.println("Execution Time: "+(end.getTime()-
                    start.getTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
