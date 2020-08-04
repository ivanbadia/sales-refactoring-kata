package com.supermarket;

import com.supermarket.domain.sales.Sale;
import com.supermarket.domain.sales.SaleLineNotValid;
import com.supermarket.domain.sales.SaleLinesReader;
import com.supermarket.infrastructure.input.Console;
import com.supermarket.infrastructure.input.ConsoleSaleLinesReader;
import com.supermarket.infrastructure.input.SaleLineParser;

public class SalesProgram {

    public static void main(String[] args) {
        Sale sale = new Sale();
        Console console = new Console();
        SaleLinesReader consoleSaleLineReader = new ConsoleSaleLinesReader(console, new SaleLineParser());
        try {
            consoleSaleLineReader.read()
                    .forEach(saleLine -> {
                        if (!sale.add(saleLine))
                            System.out.println("Sales should be in the format of <qty> <description> at <unit price>\nFor example: 2 books at 13.25");
                    });
        } catch (SaleLineNotValid e) {
            console.printLine("Nicely handled error");
        }

        console.printLine(sale.toString());
        console.printLine("--- Press Enter to Finish ---");
        console.readLine();
    }
}
