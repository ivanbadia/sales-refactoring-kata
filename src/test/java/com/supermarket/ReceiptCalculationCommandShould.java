package com.supermarket;

import com.supermarket.domain.receipt.Receipt;
import com.supermarket.domain.receipt.ReceiptCalculator;
import com.supermarket.domain.sales.*;
import com.supermarket.infrastructure.view.Output;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.supermarket.domain.sales.SaleItemBuilder.aSaleItem;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReceiptCalculationCommandShould {
    private static final List<SaleItem> SALE_LINES = List.of(aSaleItem().build());
    private static final Receipt RECEIPT = new Receipt(0, List.of());

    @Mock
    private SaleItems saleItems;

    @Mock
    private Output output;

    @Mock
    private ReceiptCalculator receiptCalculator;

    @Test
    void display_receipt() {
        given(saleItems.all()).willReturn(SALE_LINES);
        given(receiptCalculator.receiptFor(SALE_LINES)).willReturn(RECEIPT);

        new ReceiptCalculationCommand(saleItems, receiptCalculator, output).execute();

        verify(output).display(RECEIPT);
    }

    @Test
    void display_error_when_a_sale_item_is_not_valid() {
        given(saleItems.all()).willThrow(new ErrorReadingSaleItemsException());

        new ReceiptCalculationCommand(saleItems, receiptCalculator, output).execute();

        verify(output).error("It was not possible to read the sale items. Try again.");
    }
}