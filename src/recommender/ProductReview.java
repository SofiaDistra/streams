package recommender;

public class ProductReview extends Product{

    private String buyer;
    private Short value;

    public ProductReview(Product product, String buyer, Short value) {
        super(product.getId(), product.getAsin(), product.getTitle(), product.getGroup(),
                product.getSalesrank(), product.getSimilar(), product.getCategories(), product.getReviews());
        this.buyer = buyer;
        this.value = value;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Short getValue() {
        return value;
    }

    public void setValue(Short value) {
        this.value = value;
    }
}
