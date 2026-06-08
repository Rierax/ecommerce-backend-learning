package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static enums.CategoryEnum.ELECTRONICS;
import static enums.CategoryEnum.OTHERS;

public class ProductService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final static BigDecimal ELECTRONIC_PRICE = BigDecimal.valueOf(1000.00);

    public Map<String, List<Product>> groupByCategory(List<Product> products,
                                                      Function<Product, String> categoryExtractor) {
        return Map.copyOf(products.stream()
                .collect(Collectors.groupingBy(categoryExtractor)));
    }

    public Function<Product, String> electronicFilter() {
        return p
                -> p.getPrice().compareTo(ELECTRONIC_PRICE) >= 0
                ? ELECTRONICS.name()
                : OTHERS.name();
    }

    public String printValue(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
