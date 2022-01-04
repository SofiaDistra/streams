package recommender;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String id;
    private String asin;
    private String title;
    private String group;
    private Long salesrank;
    private String similar;
    private List<String> categories;
    private List<Review> reviews;

    public Product() {
    }

    public Product(String id, String asin, String title, String group, Long salesrank,
                   String similar, List<String> categories, List<Review> reviews) {
        this.id = id;
        this.asin = asin;
        this.title = title;
        this.group = group;
        this.salesrank = salesrank;
        this.similar = similar;
        this.categories = categories;
        this.reviews = reviews;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Long getSalesrank() {
        return salesrank;
    }

    public void setSalesrank(Long salesrank) {
        this.salesrank = salesrank;
    }

    public String getSimilar() {
        return similar;
    }

    public void setSimilar(String similar) {
        this.similar = similar;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addCategory(String cat) {
        if(this.categories == null) this.categories = new ArrayList<>();

        this.categories.add(cat);
    }

    public void addReview(Review review) {
        if(this.reviews == null) this.reviews = new ArrayList<>();

        this.reviews.add(review);
    }
}
