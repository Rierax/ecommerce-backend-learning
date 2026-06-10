import cache.ProductCache;
import models.Product;
import org.instancio.Instancio;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DisplayName("Тесты кэширования LRU")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductCacheTest {
    private ProductCache cache;



    @BeforeEach
    void prepare(){
        cache = new ProductCache();
    }

    @Test
    @DisplayName("тест на не изменения размера кэша")
    void cacheCapacityTest() {
        List<Product> products = Instancio
                .ofList(Product.class)
                .size(3)
                .create();

        cache.addAll(products);
        Product product = Instancio.create(Product.class);

        assertThat(cache.size()).isEqualTo(3);
        products.add(product);
        cache.addAll(products);
        assertThat(cache.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("тест на удаление старых элементов из кэша")
    void cacheRemoveDataTest() {
        List<Product> products = Instancio
                .ofList(Product.class)
                .size(3)
                .create();

        cache.addAll(products);
        Product product = Instancio.create(Product.class);

        assertThat(cache.getProduct(products.get(0).getId())).isTrue();
        products.add(product);
        cache.addAll(products);
        assertThat(cache.getProduct(products.get(0).getId())).isFalse();
    }
}
