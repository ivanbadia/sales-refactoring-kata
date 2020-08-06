package com.supermarket.infrastructure.persistence;

import com.supermarket.domain.product.Product;
import com.supermarket.domain.product.ProductName;
import com.supermarket.domain.product.ProductType;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryProductsShould {

    @Test
    void return_product() {
        Optional<Product> product = new InMemoryProducts().by(new ProductName("book"));
        assertThat(product)
                .isPresent();
        assertThat(product.get())
                .usingRecursiveComparison()
                .isEqualTo(new Product(new ProductName("book"), ProductType.BOOK));
    }
}