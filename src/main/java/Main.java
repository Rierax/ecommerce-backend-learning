import cache.ProductCache;
import com.fasterxml.jackson.core.JsonProcessingException;
import models.Product;
import services.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ProductService productService = new ProductService();
        ProductCache productCache = new ProductCache();

        List<Product> products = new ArrayList<>(List.of(
                new Product(1L, "Apple", new BigDecimal("1.50"), 100),
                new Product(2L, "Banana", new BigDecimal("0.80"), 60),
                new Product(3L, "Grape", new BigDecimal("2.50"), 10),
                new Product(4L, "Carrot", new BigDecimal("0.60"), 80),
                new Product(5L, "Laptop", new BigDecimal("1000.00"), 16),
                new Product(6L, "Smartphone", new BigDecimal("1500.00"), 12),
                new Product(7L, "Tablet", new BigDecimal("800.00"), 20),
                new Product(8L, "TV", new BigDecimal("2500.00"), 81)
        ));

        productCache.addAll(products);

        var ids = products.stream().map(Product::getId).toList();
        ids.forEach(productCache::getProduct);
        products.add(new Product(9L,"Balalayka", new BigDecimal("800"), 2));

        productCache.addAll(products);

        var ids2 = products.stream().map(Product::getId).toList();
        ids2.forEach(productCache::getProduct);

    }

}
