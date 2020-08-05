package com.supermarket.infrastructure.view;

import com.supermarket.domain.sales.Receipt;
import com.supermarket.domain.sales.ReceiptLine;

import java.text.DecimalFormat;

public class ReceiptFormatter {
    public ReceiptFormatter() {
    }

    StringBuilder format(Receipt receipt) {
        StringBuilder output = new StringBuilder();
        for (ReceiptLine line : receipt.getLines()) {
            output.append(String.format("%d %s: %s", line.getQuantity(), line.getProductName(), new DecimalFormat("#,##0.00").format(line.getAmount())));
            output.append("\n");
        }
        output.append(String.format("Sales Taxes: %s", new DecimalFormat("#,##0.00").format(receipt.getTaxes())));
        output.append("\n");
        output.append(String.format("Total: %s", new DecimalFormat("#,##0.00").format(receipt.getTotal())));
        output.append("\n");
        return output;
    }
}