package com.supermarket.domain.sales;

import java.util.List;

public class Receipt {
    private double taxes;
    private List<ReceiptLine> lines;

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
                .map(ReceiptLine::getAmount)
                .reduce(0.0, Double::sum);
    }
}
