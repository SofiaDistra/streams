package nummerical.summarization;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConcurrentMain {

    static Map<String, List<Double>> totalTimes = new
            LinkedHashMap<>();
    static List<Record> records;

    private static void measure(String name, Runnable r) {
        long start = System.nanoTime();
        r.run();
        long end = System.nanoTime();
        totalTimes.computeIfAbsent(name, k -> new
                ArrayList<>()).add((end - start) / 1_000_000.0);
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("data\\bank-additional-full.csv");
        for (int i = 0; i < 10; i++) {
            records = ConcurrentDataLoader.load(path);
            measure("Job Info", () ->
                    ConcurrentStatistics.jobDataFromSubscribers
                            (records));
            measure("Age Info", () ->
                    ConcurrentStatistics.ageDataFromSubscribers
                            (records));
            measure("Marital Info", () ->
                    ConcurrentStatistics.maritalDataFromSubscribers
                            (records));
            measure("Multiple Filter", () ->
                    ConcurrentStatistics.multipleFilterData(records));
            measure("Multiple Filter Predicate", () ->
                    ConcurrentStatistics.multipleFilterDataPredicate
                            (records));
            measure("Duration Data", () ->
                    ConcurrentStatistics.durationDataForNonSubscribers
                            (records));
            measure("Number of Contacts Bad: ", () ->
                    ConcurrentStatistics
                            .campaignDataFromNonSubscribersBad(records));
            measure("Number of Contacts", () ->
                    ConcurrentStatistics
                            .campaignDataFromNonSubscribersOk(records));
            measure("People Between 25 and 50", () ->
                    ConcurrentStatistics.peopleBetween25and50(records));

        }

         /*times.stream().map(t -> String.format("%6.2f",
                t)).collect(Collectors.joining(" ")), times
                .stream().mapToDouble
                        (Double::doubleValue).average().getAsDouble()));*/
    }
}
