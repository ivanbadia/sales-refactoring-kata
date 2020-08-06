package com.supermarket.domain.receipt;

import com.supermarket.domain.sales.SaleLine;

import java.util.ArrayList;
import java.util.List;

public class ReceiptCalculator {
    private TaxRateProvider taxRateProvider;

    public ReceiptCalculator(TaxRateProvider taxRateProvider) {
        this.taxRateProvider = taxRateProvider;
    }

    public Receipt receiptFor(List<SaleLine> saleLines) {
        double totalTaxAmount = 0.0;

        List<ReceiptLine> lines = new ArrayList<>();
        for (SaleLine saleLine : saleLines) {
            int taxRate = taxRateProvider.taxRateFor(saleLine);
            double saleLineTax = CalculateTax(saleLine.getTotalAmount(), taxRate);
            lines.add(new ReceiptLine(saleLine.getQuantity(), saleLine.getProductName(), saleLine.getTotalAmount() + saleLineTax, saleLine.isImported()));
            totalTaxAmount += saleLineTax;
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
