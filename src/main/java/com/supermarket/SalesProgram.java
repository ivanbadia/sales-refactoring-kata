package com.supermarket;

import com.supermarket.domain.sales.ReceiptCalculator;
import com.supermarket.domain.sales.SaleLines;
import com.supermarket.infrastructure.view.*;

public class SalesProgram {

    public static void main(String[] args) {
        Console console = new Console();
        SaleLines saleLines = new ConsoleSaleLinesReader(console, new SaleLineParser());
        Output output = new ConsoleOutput(console, new ReceiptFormatter());
        new ReceiptCalculationCommand(saleLines, new ReceiptCalculator(), output).execute();
    }
}
