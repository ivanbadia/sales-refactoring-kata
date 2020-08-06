package com.supermarket.infrastructure.persistence;

import com.supermarket.domain.product.Product;
import com.supermarket.domain.product.ProductName;
import com.supermarket.domain.product.ProductType;
import com.supermarket.domain.product.Products;

import java.util.List;
import java.util.Optional;

public class InMemoryProducts implements Products {
    private static final List<Product> PRODUCTS = List.of(
            new Product(new ProductName("book"), ProductType.BOOK),
            new Product(new ProductName("packet of chips"), ProductType.FOOD),
            new Product(new ProductName("box of chips"), ProductType.FOOD),
            new Product(new ProductName("packet of headache tablets"), ProductType.MEDICAL_ITEM),
            new Product(new ProductName("imported box of chocolates"), ProductType.FOOD),
            new Product(new ProductName("imported bottles of whiskey"), ProductType.FOOD),
            new Product(new ProductName("bottles of local whiskey"), ProductType.FOOD),
            new Product(new ProductName("packets of iodine tablets"), ProductType.MEDICAL_ITEM),
            new Product(new ProductName("imported boxes of potato chips"), ProductType.MEDICAL_ITEM)
    );


    @Override
    public Optional<Product> by(ProductName name) {
        return PRODUCTS.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst();
    }
}
