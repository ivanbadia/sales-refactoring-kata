package com.supermarket.infrastructure.view;

import com.supermarket.domain.product.ProductName;
import com.supermarket.domain.sales.SaleLine;

import java.util.Optional;

public class SaleLineParser {

    public static final int QUANTITY_POSITION = 0;

    public Optional<SaleLine> parse(String input) {
        String[] words = input.split(" ");
        if (hasLessThanFourWords(words)) {
            return Optional.empty();
        }
        return saleLineFrom(words);
    }

    private Optional<SaleLine> saleLineFrom(String[] words) {
        try {
            String productName = productNameFrom(words);
            if (productName.isEmpty()) {
                return Optional.empty();
            }

            int quantity = Integer.parseInt(words[QUANTITY_POSITION]);
            double price = Double.parseDouble(lastWordOf(words));

            boolean isImported = productName.contains("imported ");
            if (isImported) {
                productName = removeImportedFrom(productName);
            }

            return Optional.of(new SaleLine(quantity, new ProductName(productName), price, isImported));

        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private String removeImportedFrom(String productName) {
        return productName.replace("imported ", "");
    }

    private String productNameFrom(String[] words) {
        String productName = "";
        for (int i = 1; i < words.length - 2; i++) {
            productName = String.join(" ", productName, words[i]);
        }
        productName = productName.trim();
        return productName;
    }

    private String lastWordOf(String[] words) {
        return words[words.length - 1];
    }

    private boolean hasLessThanFourWords(String[] words) {
        return words.length < 4;
    }

}
