package com.supermarket.infrastructure.view;

import com.supermarket.domain.sales.Receipt;

public class ConsoleOutput implements Output {
    private final ReceiptFormatter receiptFormatter;
    private final Console console;

    public ConsoleOutput(Console console, ReceiptFormatter receiptFormatter) {
        this.console = console;
        this.receiptFormatter = receiptFormatter;
    }

    @Override
    public void display(Receipt receipt) {
        StringBuilder output = receiptFormatter.format(receipt);
        console.printLine(output.toString());
        console.printLine("--- Press Enter to Finish ---");
        console.readLine();
    }

    @Override
    public void error(String errorMessage) {
        console.printLine(errorMessage);
    }

}
