package bio;

public class FastQ {

    private String name;
    private String nucSequence;
    private String qualitySequence;
    private Double avgQuality;

    public FastQ() {
    }

    public FastQ(String name, String nucSequence, String qualitySequence) {
        this.name = name;
        this.nucSequence = nucSequence;
        this.qualitySequence = qualitySequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNucSequence() {
        return nucSequence;
    }

    public void setNucSequence(String nucSequence) {
        this.nucSequence = nucSequence;
    }

    public String getQualitySequence() {
        return qualitySequence;
    }

    public void setQualitySequence(String qualitySequence) {
        this.qualitySequence = qualitySequence;
    }

    public Double getAvgQuality() {
        return avgQuality;
    }

    public void setAvgQuality(Double avgQuality) {
        this.avgQuality = avgQuality;
    }

    @Override
    public String toString() {
        return "FastQ{" +
                "name='" + name + '\'' +
                ", avgQuality='" + avgQuality + '\'' +
                '}';
    }
}
