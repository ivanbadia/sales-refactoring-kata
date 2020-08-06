package com.supermarket.domain.receipt;

import com.supermarket.domain.product.Product;
import com.supermarket.domain.product.Products;
import com.supermarket.domain.sales.SaleLine;

import java.util.function.Function;

import static com.supermarket.domain.product.ProductType.*;

public class TaxRateProvider {
    private static final int NO_BASE_TAX = 0;
    private static final int BASE_TAX = 10;
    private static final int EXTRA_TAX_FOR_IMPORTED_PRODUCTS = 5;

    private final Products products;

    public TaxRateProvider(Products products) {
        this.products = products;
    }

    public int taxRateFor(SaleLine saleLine) {
        int taxRate = products.by(saleLine.getProductName())
                .map(toTaxRateForProduct())
                .orElse(BASE_TAX);

        if (saleLine.isImported()) {
            return taxRate + EXTRA_TAX_FOR_IMPORTED_PRODUCTS;
        } else {
            return taxRate;
        }
    }

    private Function<Product, Integer> toTaxRateForProduct() {
        return product -> {
            if (isExemptOfTaxes(product)) {
                return NO_BASE_TAX;
            }
            return BASE_TAX;
        };
    }

    private boolean isExemptOfTaxes(Product product) {
        return product.getType().equals(BOOK)
                || product.getType().equals(MEDICAL_ITEM)
                || product.getType().equals(FOOD);
    }

}
