import models.Product;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import services.CartService;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.field;

@DisplayName("Тесты сервиса карты")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CartServiceTest {

    private final Long firstUserId = 1L;

    CartService cartService;

    @BeforeEach
    void prepareDate() {
        cartService = new CartService();
    }

    @Test
    @DisplayName("Тест на пустую корзину")
    void addProductToCartTest() {
        Product product = Instancio.create(Product.class);

        cartService.addToCart(firstUserId, product, 0);

        assertThat(cartService.calculateTotal(firstUserId))
                .isEqualTo(BigDecimal.ZERO);

    }

    @Test
    @DisplayName("Комплексный тест на добавление / удаление / подсчет суммы")
    void removeFromCartTest() {
        Product productOne = Instancio.of(Product.class)
                .set(field(Product::getPrice), new BigDecimal("10"))
                .create();

        Product productTwo = Instancio.of(Product.class)
                .set(field(Product::getPrice), new BigDecimal("20"))
                .create();
        cartService.addToCart(firstUserId, productOne, 3);
        cartService.addToCart(firstUserId, productTwo, 1);
        assertThat(cartService.calculateTotal(firstUserId)).isEqualTo(new BigDecimal("50"));
        cartService.removeFromCart(firstUserId, productTwo.getId());
        assertThat(cartService.calculateTotal(firstUserId)).isEqualTo(new BigDecimal("30"));
    }
}
