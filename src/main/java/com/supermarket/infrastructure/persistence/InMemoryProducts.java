package com.supermarket.infrastructure.persistence;

import com.supermarket.domain.product.Product;
import com.supermarket.domain.product.ProductName;
import com.supermarket.domain.product.Products;

import java.util.List;
import java.util.Optional;

import static com.supermarket.domain.product.ProductType.*;

public class InMemoryProducts implements Products {
    private static final List<Product> PRODUCTS = List.of(
            new Product(new ProductName("book"), BOOK),
            new Product(new ProductName("packet of chips"), FOOD),
            new Product(new ProductName("box of chips"), FOOD),
            new Product(new ProductName("boxes of potato chips"), FOOD),
            new Product(new ProductName("packet of headache tablets"), MEDICAL_ITEM),
            new Product(new ProductName("box of chocolates"), FOOD),
            new Product(new ProductName("packets of iodine tablets"), MEDICAL_ITEM)
    );


    @Override
    public Optional<Product> by(ProductName name) {
        return PRODUCTS.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst();
    }
}
