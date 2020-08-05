package com.supermarket.domain.sales;

import java.util.ArrayList;
import java.util.List;

public class ReceiptCalculator {
    public Receipt receiptFor(List<SaleLine> saleLines) {

        double totalTaxAmount = 0.0;

        List<ReceiptLine> lines = new ArrayList<>();
        for (SaleLine saleLine : saleLines) {
            int taxRate;
            //TODO
            if (saleLine.getProductName().contains("book") || saleLine.getProductName().contains("tablet") || saleLine.getProductName().contains("chip") || saleLine.getProductName().contains("chocolate"))
                taxRate = 0;  //No base tax applicable for books, medicals items or food
            else
                taxRate = 10; //10% base tax or general products
            if (saleLine.isImported())
                taxRate += 5; //5% regardless for any imported items

            //TODO
            double lineTax = CalculateTax(saleLine.getPrice() * saleLine.getQuantity(), taxRate);
            lines.add(new ReceiptLine(saleLine.getQuantity(), saleLine.getProductName(), (saleLine.getPrice() * saleLine.getQuantity()) + lineTax));
            totalTaxAmount += lineTax;
        }

        return new Receipt(totalTaxAmount, lines);
    }


    public static double CalculateTax(double value, int taxRate) {
        double amount = (value * taxRate) / 100;
        return roundUpToNearestFiveCents(amount);
    }

    private static double roundUpToNearestFiveCents(double amount) {
        double remainder = amount % .05;
        if (remainder > 0)
            amount += .05 - remainder;
        return amount;
    }
}
