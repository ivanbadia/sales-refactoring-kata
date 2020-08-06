package com.supermarket.domain.receipt;

import java.util.List;

public class Receipt {
    private final double taxes;
    private final List<ReceiptLine> lines;

    public Receipt(double taxes, List<ReceiptLine> lines) {
        this.taxes = taxes;
        this.lines = lines;
    }

    public List<ReceiptLine> getLines() {
        return this.lines;
    }

    public double getTaxes() {
        return taxes;
    }

    public double getTotal() {
        return lines.stream()
                .map(ReceiptLine::getTotalAmount)
                .reduce(0.0, Double::sum);
    }
}
