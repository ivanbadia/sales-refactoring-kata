package com.supermarket.domain.product;

public class Product {
    private final ProductName name;
    private final ProductType type;

    public Product(ProductName name, ProductType type) {
        this.name = name;
        this.type = type;
    }

    public ProductName getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }
}
