package com.supermarket.domain.product;

import java.util.Optional;

public interface Products {
    Optional<Product> by(ProductName name);
}
