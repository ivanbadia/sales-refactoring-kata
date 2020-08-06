package com.supermarket;

import com.supermarket.domain.receipt.ReceiptCalculator;
import com.supermarket.domain.receipt.TaxRateProvider;
import com.supermarket.domain.sales.SaleItems;
import com.supermarket.infrastructure.persistence.InMemoryProducts;
import com.supermarket.infrastructure.view.*;

public class SalesProgram {

    public static void main(String[] args) {
        Console console = new Console();
        SaleItems saleItems = new ConsoleSaleItemsReader(console, new SaleItemParser());
        Output output = new ConsoleOutput(console, new ReceiptFormatter());
        ReceiptCalculator receiptCalculator = new ReceiptCalculator(new TaxRateProvider(new InMemoryProducts()));

        new ReceiptCalculationCommand(saleItems, receiptCalculator, output).execute();
    }
}
