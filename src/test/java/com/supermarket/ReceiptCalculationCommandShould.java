package com.supermarket;

import com.supermarket.domain.sales.*;
import com.supermarket.infrastructure.view.Output;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReceiptCalculationCommandShould {
    private static final List<SaleLine> SALE_LINES = List.of(new SaleLine(1, "book", 12.49, false));
    private static final Receipt RECEIPT = new Receipt(0, List.of());

    @Mock
    private SaleLines saleLines;

    @Mock
    private Output output;

    @Mock
    private ReceiptCalculator receiptCalculator;

    @Test
    void display_receipt() {
        given(saleLines.all()).willReturn(SALE_LINES);
        given(receiptCalculator.receiptFor(SALE_LINES)).willReturn(RECEIPT);

        new ReceiptCalculationCommand(saleLines, receiptCalculator, output).execute();

        verify(output).display(RECEIPT);
    }

    @Test
    void display_error_when_a_sale_item_is_not_valid() {
        given(saleLines.all()).willThrow(new ErrorReadingSaleLinesException());

        new ReceiptCalculationCommand(saleLines, receiptCalculator, output).execute();

        verify(output).error("It was not possible to read the sale lines. Try again.");
    }
}