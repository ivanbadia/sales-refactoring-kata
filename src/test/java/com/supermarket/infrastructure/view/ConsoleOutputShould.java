package com.supermarket.infrastructure.view;

import com.supermarket.domain.sales.Receipt;
import com.supermarket.domain.sales.ReceiptLine;
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
                        new ReceiptLine(1, "box of chips", 12.3),
                new ReceiptLine(2, "music CD", 44.60))
        );


        new ConsoleOutput(console, new ReceiptFormatter()).display(receipt);

        String expectedReceipt = "1 box of chips: 12.30\n" +
                "2 music CD: 44.60\n" +
                "Sales Taxes: 5.70\n" +
                "Total: 56.90\n";
        verify(console).printLine(expectedReceipt);
        verify(console).printLine("--- Press Enter to Finish ---");
        verify(console).readLine();
    }
}