package search;

import common.Product;
import recommender.product.ProductLoader;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.BiConsumer;

public class ConcurrentObjectAccumulator implements BiConsumer<ConcurrentLinkedDeque<Product>, Path> {

    private String word;

    public ConcurrentObjectAccumulator(String word) {
        this.word = word;
    }

    @Override
    public void accept(ConcurrentLinkedDeque<Product> list, Path path) {
        Product product= ProductLoader.load(path);
        if (product.getTitle().toLowerCase().contains
                (word.toLowerCase())) {
            list.add(product);
        }
    }
}
