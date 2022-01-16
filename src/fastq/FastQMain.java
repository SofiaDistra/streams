package fastq;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FastQMain {

    public static void main(String[] args)  {

        try {

            Date start, end;
            start = new Date();

            // read file and create the list of FastQs using FastQCollector
            List<FastQ> fastQList = Files.lines(Path.of("resources/100K.fastq"))
                    .sequential()
                    .collect(FastQCollector.collector())
                    .stream()
                    .parallel()
                    .map(f -> {
                                // create a new FastQ object
                                FastQ newF = new FastQ(f.getName(),f.getNucSequence(), f.getQualitySequence());

                                // set the avgQuality for the new FastQ
                                newF.setAvgQuality((f.getQualitySequence().chars()
                                        .map(c -> c-33).sum() / (double)f.getQualitySequence().chars().count()));
                                return newF;
                            })
                    .collect(Collectors.toList());

            // fastQList.forEach(System.out::println);
            end = new Date();
            System.out.println("Execution Time: " + (end.getTime() -
                    start.getTime()));
        }
        catch (IOException e) {

        }




    }
}
