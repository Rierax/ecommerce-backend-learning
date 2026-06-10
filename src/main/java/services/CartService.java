package services;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import models.Product;

import java.math.BigDecimal;
import java.util.*;

import static java.util.Objects.isNull;

@Slf4j
public class CartService {

    private final Map<Long, List<Product>> carts = new HashMap<>();

    public void addToCart(@NonNull Long userId,
                          @NonNull Product product,
                          int quantity) {
        var products = new ArrayList<>(Collections.nCopies(quantity, product));
        var cart = carts.get(userId);
        if (isNull(cart)) {
            carts.put(userId, products);
        } else {
            cart.addAll(products);
        }
    }

    public void removeFromCart(@NonNull Long userId,
                               Long productId) {
        var products = carts.get(userId);

        if (!isNull(products)) {
            products.stream()
                    .filter(fil -> fil.getId().equals(productId))
                    .findFirst()
                    .ifPresentOrElse(products::remove,
                            () -> log.info("Продукт с id: {}, для клиента с id: {}, не был найден", productId, userId));
        } else log.info("Клиент с id: {} не был найден", userId);
    }

    public BigDecimal calculateTotal(@NonNull Long userId) {
        return carts.get(userId)
                .stream()
                .map(Product::getPrice)
                .toList()
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
