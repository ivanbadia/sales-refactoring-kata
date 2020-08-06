package com.supermarket.infrastructure.persistence;

import com.supermarket.domain.product.Product;
import com.supermarket.domain.product.ProductName;
import com.supermarket.domain.product.ProductType;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryProductsShould {

    private static final ProductName BOOK = new ProductName("book");

    @Test
    void return_product() {
        Optional<Product> product = new InMemoryProducts().by(BOOK);

        assertThat(product)
                .isPresent();
        assertThat(product.get())
                .usingRecursiveComparison()
                .isEqualTo(new Product(BOOK, ProductType.BOOK));
    }
}