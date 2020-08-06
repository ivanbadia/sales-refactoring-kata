package com.supermarket.domain.sales;

import com.supermarket.domain.product.ProductName;
import com.supermarket.domain.receipt.Receipt;
import com.supermarket.domain.receipt.ReceiptCalculator;
import com.supermarket.domain.receipt.ReceiptLine;
import com.supermarket.domain.receipt.TaxRateProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ReceiptCalculatorShould {

    public static final ProductName MUSIC_CD = new ProductName("music CD");
    public static final ProductName BOTTLE_OF_PERFUME = new ProductName("bottle of perfume");

    @Mock
    private TaxRateProvider taxRateProvider;

    private ReceiptCalculator receiptCalculator;

    @BeforeEach
    void setUp() {
        receiptCalculator = new ReceiptCalculator(taxRateProvider);
    }

    @Test
    void calculate_receipt() {
        List<SaleLine> saleLines = List.of(
                new SaleLine(1, MUSIC_CD, 12.49, false),
                new SaleLine(2, BOTTLE_OF_PERFUME, 19.05, true)
        );
        given(taxRateProvider.taxRateFor(saleLines.get(0))).willReturn(10);
        given(taxRateProvider.taxRateFor(saleLines.get(1))).willReturn(15);

        Receipt receipt = receiptCalculator.receiptFor(saleLines);

        Receipt expectedReceipt = new Receipt(
                7.0,
                List.of(
                        new ReceiptLine(1, MUSIC_CD, 13.74, false),
                        new ReceiptLine(2, BOTTLE_OF_PERFUME, 43.85, true)
                )
        );
        assertThat(receipt)
                .usingRecursiveComparison()
                .isEqualTo(expectedReceipt);
    }
}