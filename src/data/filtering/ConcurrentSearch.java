package data.filtering;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class ConcurrentSearch {

    /*public static void basicSearch(String query[]) throws
            IOException {
        Path path = Paths.get("index", "invertedIndex.txt");
        HashSet<String> set = new HashSet<>(Arrays.asList(query));
        QueryResult results = new QueryResult(new
                ConcurrentHashMap<>());
        try (Stream<String> invertedIndex = Files.lines(path)) {
            invertedIndex.parallel()
                    .filter(line -> set.contains(Utils.getWord(line)))
                    .flatMap(ConcurrentSearch::basicMapper)
                    .forEach(results::append);
            results
                    .getAsList()
                    .stream()
                    .sorted()
                    .limit(100)
                    .forEach(System.out::println);
            System.out.println("Basic Search Ok");
        }
    }*/
}
