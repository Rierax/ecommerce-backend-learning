package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Product {

    @NonNull
    private final Long id;
    private String name;
    private BigDecimal price;
    private int stock;
}
