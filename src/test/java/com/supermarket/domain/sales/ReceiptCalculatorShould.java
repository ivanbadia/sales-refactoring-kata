package com.supermarket.domain.sales;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReceiptCalculatorShould {

    @Test
    void calculate_receipt() {

        List<SaleLine> saleLines = List.of(
                new SaleLine(1, "music CD", 12.49, false),
                new SaleLine(2, "bottle of perfume", 19.05, false)
        );

        Receipt receipt = new ReceiptCalculator().receiptFor(saleLines);

        Receipt expectedReceipt = new Receipt(
                5.1,
                List.of(
                        new ReceiptLine(1, "music CD", 13.74),
                        new ReceiptLine(2, "bottle of perfume", 41.95)
                )
        );


        assertThat(receipt)
                .usingRecursiveComparison()
                .isEqualTo(expectedReceipt);
    }
}