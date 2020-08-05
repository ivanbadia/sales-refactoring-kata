package com.supermarket;

import com.supermarket.domain.sales.*;
import com.supermarket.infrastructure.view.Output;

public class ReceiptCalculationCommand {
    private final SaleLines saleLines;
    private final ReceiptCalculator receiptCalculator;
    private final Output output;

    public ReceiptCalculationCommand(SaleLines saleLines, com.supermarket.domain.sales.ReceiptCalculator receiptCalculator, Output output) {
        this.saleLines = saleLines;
        this.receiptCalculator = receiptCalculator;
        this.output = output;
    }

    public void execute() {
        try {
            Receipt receipt = receiptCalculator.receiptFor(saleLines.all());
            output.display(receipt);
        } catch (ErrorReadingSaleLinesException e) {
            output.error("It was not possible to read the sale lines. Try again.");
        }
    }
}
