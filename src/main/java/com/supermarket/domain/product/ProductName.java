package com.supermarket.domain.product;

import java.util.Objects;

public class ProductName {
    private String value;

    public ProductName(String value) {
        this.value = value;
    }

    public String asString() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductName that = (ProductName) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
