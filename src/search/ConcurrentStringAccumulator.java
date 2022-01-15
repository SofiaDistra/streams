package recommender;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class ConcurrentStringAccumulator implements BiConsumer<ConcurrentLinkedDeque<String>, Path> {

    private String word;

    public ConcurrentStringAccumulator(String word) {
        this.word=word.toLowerCase();
    }

    @Override
    public void accept(ConcurrentLinkedDeque<String> list, Path path) {
        boolean result;
        try (Stream<String> lines = Files.lines(path)) {
            result = lines
                    .parallel()
                    .map(l -> l.split(":")[1].toLowerCase())
                    .anyMatch(l -> l.contains(word));
            if (result) {
                list.add(path.toString());
            }
        } catch (Exception e) {
            System.out.println(path);
            e.printStackTrace();
        }
    }
}
