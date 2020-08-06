package com.supermarket.infrastructure.view;

import com.supermarket.domain.receipt.Receipt;
import com.supermarket.domain.receipt.ReceiptLine;
import com.supermarket.domain.shared.Money;

import java.text.DecimalFormat;
import java.util.function.Consumer;
import java.util.function.Function;

public class ReceiptFormatter {
    public ReceiptFormatter() {
    }

    StringBuilder format(Receipt receipt) {
        StringBuilder output = new StringBuilder();

        receipt.getLines()
                .stream()
                .map(toOutputLine())
                .forEach(addTo(output));

        return output
                .append(salesTaxesLine(receipt.getTaxes()))
                .append(System.lineSeparator())
                .append(totalAmountLine(receipt.getTotal()))
                .append(System.lineSeparator());
    }

    private String totalAmountLine(Money total) {
        return String.format("Total: %s", new DecimalFormat("#,##0.00").format(total.asDouble()));
    }

    private String salesTaxesLine(Money taxes) {
        return String.format("Sales Taxes: %s", new DecimalFormat("#,##0.00").format(taxes.asDouble()));
    }

    private Consumer<String> addTo(StringBuilder output) {
        return line -> {
            output.append(line);
            output.append(System.lineSeparator());
        };
    }

    private Function<ReceiptLine, String> toOutputLine() {
        return line -> {
            String description = line.getProductName().asString();
            if (line.isImported()) {
                description = "imported " + description;
            }
            return String.format("%d %s: %s", line.getQuantity(), description, new DecimalFormat("#,##0.00").format(line.getTotalAmount().asDouble()));
        };
    }
}