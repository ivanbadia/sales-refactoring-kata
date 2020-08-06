package com.supermarket.infrastructure.view;

import com.supermarket.domain.receipt.Receipt;
import com.supermarket.domain.receipt.ReceiptLine;

import java.text.DecimalFormat;

public class ReceiptFormatter {
    public ReceiptFormatter() {
    }

    StringBuilder format(Receipt receipt) {
        StringBuilder output = new StringBuilder();
        for (ReceiptLine line : receipt.getLines()) {
            String description = line.getProductName().asString();
            if(line.isImported()) {
                description = "imported " + description;
            }
            output.append(String.format("%d %s: %s", line.getQuantity(), description, new DecimalFormat("#,##0.00").format(line.getTotalAmount().asDouble())));
            output.append("\n");
        }
        output.append(String.format("Sales Taxes: %s", new DecimalFormat("#,##0.00").format(receipt.getTaxes())));
        output.append("\n");
        output.append(String.format("Total: %s", new DecimalFormat("#,##0.00").format(receipt.getTotal().asDouble())));
        output.append("\n");
        return output;
    }
}