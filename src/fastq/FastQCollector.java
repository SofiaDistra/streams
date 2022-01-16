package fastq;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

// Custom Collector to read file by 4 lines
public class FastQCollector {
    private final List<FastQ> list = new ArrayList<>();

    private List<String> accumulateList = new ArrayList<>();

    public void accept(String str) {
        accumulateList.add(str);
        if (accumulateList.size() == 4) { // accumulate 4 strings

            FastQ fastQ = new FastQ(accumulateList.get(0), accumulateList.get(1), accumulateList.get(3));
            list.add(fastQ);

            accumulateList = new ArrayList<>();
        }
    }

    public List<FastQ> finish() {
        return list;
    }

    public static Collector<String, ?, List<FastQ>> collector() {
        return Collector.of(FastQCollector::new, FastQCollector::accept, (a, b) -> a, FastQCollector::finish);
    }

}
