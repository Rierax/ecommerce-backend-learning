import com.fasterxml.jackson.core.JsonProcessingException;
import models.Product;
import services.ProductService;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {

        ProductService productService = new ProductService();

        List<Product> products = List.of(
                new Product(1L, "Apple", new BigDecimal("1.50"), 100),
                new Product(2L, "Banana", new BigDecimal("0.80"), 60),
                new Product(3L, "Grape", new BigDecimal("2.50"), 10),
                new Product(4L, "Carrot", new BigDecimal("0.60"), 80),
                new Product(5L, "Laptop", new BigDecimal("1000.00"), 16),
                new Product(6L, "Smartphone", new BigDecimal("1500.00"), 12),
                new Product(7L, "Tablet", new BigDecimal("800.00"), 20),
                new Product(8L, "TV", new BigDecimal("2500.00"), 81)
        );

        var result = productService.groupByCategory(products, productService.electronicFilter());

        productService.printValue(result);
    }

}
