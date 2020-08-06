package com.supermarket.infrastructure.view;

import com.supermarket.domain.product.ProductName;
import com.supermarket.domain.receipt.Receipt;
import com.supermarket.domain.receipt.ReceiptLine;
import com.supermarket.domain.shared.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConsoleOutputShould {

    @Mock
    private Console console;

    @Test
    void display_receipt() {
        Receipt receipt = new Receipt(
                5.70,
                List.of(
                        new ReceiptLine(1, new ProductName("box of chips"), new Money(12.3), false),
                new ReceiptLine(2, new ProductName("music CD"), new Money(44.60), true))
        );


        new ConsoleOutput(console, new ReceiptFormatter()).display(receipt);

        String expectedReceipt = "1 box of chips: 12.30\n" +
                "2 imported music CD: 44.60\n" +
                "Sales Taxes: 5.70\n" +
                "Total: 56.90\n";
        verify(console).printLine(expectedReceipt);
        verify(console).printLine("--- Press Enter to Finish ---");
        verify(console).readLine();
    }
}