package cache;

import lombok.extern.slf4j.Slf4j;
import models.Product;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ProductCache extends LinkedHashMap<Long, Product> {

    private static final int CAPACITY = 3;

    public ProductCache() {
        super(CAPACITY, 0.75f, true);
    }

    public boolean removeEldestEntry(Map.Entry<Long, Product> eldest) {
        return size() > CAPACITY;
    }

    public void addAll(List<Product> products) {
        for (Product product : products) {
            put(product.getId(), product);
        }
    }

    public boolean getProduct(Long id) {
        if (!containsKey(id)) {
            log.info("Не найдено записи в кэше с идентификатором: {}, осуществляем поиск в бд", id);
            return false;
        } else {
            log.info("Достаем запись с идентификатором {} из кэша", id);
            return true;
        }
    }

}
