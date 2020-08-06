package com.supermarket.domain.receipt;

import com.supermarket.domain.sales.SaleItem;
import com.supermarket.domain.shared.Money;

import java.util.ArrayList;
import java.util.List;

public class ReceiptCalculator {
    private TaxRateProvider taxRateProvider;

    public ReceiptCalculator(TaxRateProvider taxRateProvider) {
        this.taxRateProvider = taxRateProvider;
    }

    public Receipt receiptFor(List<SaleItem> saleItems) {
        double totalTaxAmount = 0.0;

        List<ReceiptLine> lines = new ArrayList<>();
        for (SaleItem saleItem : saleItems) {
            int taxRate = taxRateProvider.taxRateFor(saleItem);
            double saleItemTax = CalculateTax(saleItem.getTotalAmount(), taxRate);
            lines.add(new ReceiptLine(saleItem.getQuantity(), saleItem.getProductName(), new Money(saleItem.getTotalAmount().asDouble() + saleItemTax), saleItem.isImported()));
            totalTaxAmount += saleItemTax;
        }

        return new Receipt(totalTaxAmount, lines);
    }


    public static double CalculateTax(Money value, int taxRate) {
        double amount = (value.asDouble() * taxRate) / 100;
        return roundUpToNearestFiveCents(amount);
    }

    private static double roundUpToNearestFiveCents(double amount) {
        double remainder = amount % .05;
        if (remainder > 0)
            amount += .05 - remainder;
        return amount;
    }
}
