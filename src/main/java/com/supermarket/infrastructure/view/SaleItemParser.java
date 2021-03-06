package com.supermarket.infrastructure.view;

import com.supermarket.domain.product.ProductName;
import com.supermarket.domain.sales.SaleItem;
import com.supermarket.domain.shared.Money;

import java.util.Optional;

import static java.lang.Double.parseDouble;

public class SaleItemParser {

    public static final int QUANTITY_POSITION = 0;

    public Optional<SaleItem> parse(String input) {
        String[] words = input.split(" ");
        if (hasLessThanFourWords(words)) {
            return Optional.empty();
        }
        return saleItemFrom(words);
    }

    private Optional<SaleItem> saleItemFrom(String[] words) {
        try {
            String productName = productNameFrom(words);
            if (productName.isEmpty()) {
                return Optional.empty();
            }

            boolean isImported = productName.contains("imported ");
            if (isImported) {
                productName = removeImportedWordFrom(productName);
            }

            int quantity = Integer.parseInt(words[QUANTITY_POSITION]);
            double price = parseDouble(lastWordOf(words));

            return Optional.of(new SaleItem(quantity, new ProductName(productName), new Money(price), isImported));

        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private String removeImportedWordFrom(String productName) {
        return productName.replace("imported ", "");
    }

    private String productNameFrom(String[] words) {
        String productName = "";
        for (int i = 1; i < words.length - 2; i++) {
            productName = String.join(" ", productName, words[i]);
        }
        return productName.trim();
    }

    private String lastWordOf(String[] words) {
        return words[words.length - 1];
    }

    private boolean hasLessThanFourWords(String[] words) {
        return words.length < 4;
    }

}
