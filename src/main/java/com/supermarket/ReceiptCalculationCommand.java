package com.supermarket;

import com.supermarket.domain.receipt.Receipt;
import com.supermarket.domain.receipt.ReceiptCalculator;
import com.supermarket.domain.sales.*;
import com.supermarket.infrastructure.view.Output;

public class ReceiptCalculationCommand {
    private final SaleItems saleItems;
    private final ReceiptCalculator receiptCalculator;
    private final Output output;

    public ReceiptCalculationCommand(SaleItems saleItems, ReceiptCalculator receiptCalculator, Output output) {
        this.saleItems = saleItems;
        this.receiptCalculator = receiptCalculator;
        this.output = output;
    }

    public void execute() {
        try {
            Receipt receipt = receiptCalculator.receiptFor(saleItems.all());
            output.display(receipt);
        } catch (ErrorReadingSaleItemsException e) {
            output.error("It was not possible to read the sale items. Try again.");
        }
    }
}
