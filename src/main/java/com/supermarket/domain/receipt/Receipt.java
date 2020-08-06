package com.supermarket.domain.receipt;

import com.supermarket.domain.shared.Money;

import java.util.List;

public class Receipt {
    private final Money taxes;
    private final List<ReceiptLine> lines;

    public Receipt(Money taxes, List<ReceiptLine> lines) {
        this.taxes = taxes;
        this.lines = lines;
    }

    public List<ReceiptLine> getLines() {
        return this.lines;
    }

    public Money getTaxes() {
        return taxes;
    }

    public Money getTotal() {
        return lines.stream()
                .map(ReceiptLine::getTotalAmount)
                .reduce(new Money(0), Money::sum);
    }
}
