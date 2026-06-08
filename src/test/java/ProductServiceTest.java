import models.Product;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import services.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static enums.CategoryEnum.ELECTRONICS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.field;

@DisplayName("Тесты сервиса работы с продуктами")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceTest {

    ProductService productService;

    @BeforeEach
    void prepare() {
        productService = new ProductService();
    }

    @Test
    @DisplayName("Тест когда все товары стоят больше 1000")
    void electronicFilter_More1000Test() {
        List<Product> products = Instancio.ofList(Product.class)
                .size(10)
                .set(field(Product::getPrice), new BigDecimal("1000"))
                .create();
        Set<String> filtered = productService.groupByCategory(
                products, productService.electronicFilter()).keySet();
        assertThat(filtered).contains(ELECTRONICS.name());
    }

    @Test
    @DisplayName("Тест когда все товары стоят меньше 1000")
    void electronicFilter_noOneNore1000Test() {
        List<Product> products = Instancio.ofList(Product.class)
                .size(10)
                .set(field(Product::getPrice), new BigDecimal("999"))
                .create();
        Set<String> filtered = productService.groupByCategory(
                products, productService.electronicFilter()).keySet();
        assertThat(filtered).doesNotContain(ELECTRONICS.name());
    }
}
