package recommender.product;

public class ProductRecommendation implements Comparable {

    private String title;
    private Double value;

    public ProductRecommendation(String title, Double value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof ProductRecommendation) {
            ProductRecommendation pr = (ProductRecommendation) o;
            return this.value.compareTo(pr.getValue());
        }
        return -1;
    }
}
