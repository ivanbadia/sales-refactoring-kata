package com.supermarket.domain.sales;

import com.supermarket.domain.product.ProductName;
import com.supermarket.infrastructure.view.Console;
import com.supermarket.infrastructure.view.ConsoleSaleItemsReader;
import com.supermarket.infrastructure.view.SaleItemParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static com.supermarket.domain.sales.SaleItemBuilder.aSaleItem;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ConsoleSaleItemsReaderShould {

    private InputStream stdin;

    @BeforeEach
    void setUp() {
        stdin = System.in;
    }

    @AfterEach
    void afterAll() {
        System.setIn(stdin);
    }

    @Test
    void read_sale_items() {
        writeInput("1 book at 12.49\n1 music CD at 14.99\n2 box of chips at 10.00\n\n");

        List<SaleItem> saleItems = saleItemReader().all();

        assertThat(saleItems)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(
                        aSaleItem().withQuantity(1).withName(new ProductName("book")).withUnitPrice(12.49).build(),
                        aSaleItem().withQuantity(1).withName(new ProductName("music CD")).withUnitPrice(14.99).build(),
                        aSaleItem().withQuantity(2).withName(new ProductName("box of chips")).withUnitPrice(10.00).build());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1 book 12.49", "a book at 12.49", "1 book at d", "1    at 12.49"})
    void fail_if_sale_item_is_not_valid(String input) {
        writeInput(input);

        Throwable exception = catchThrowable(() -> saleItemReader().all());

        assertThat(exception).isInstanceOf(ErrorReadingSaleItemsException.class);
    }

    @Test
    void read_imported_sale_items() {
        writeInput("1 box of imported chocolates at 11.25");

        List<SaleItem> saleItems = saleItemReader().all();

        SaleItem expectedSaleItem = aSaleItem()
                .withQuantity(1)
                .withName(new ProductName("box of chocolates"))
                .withUnitPrice(11.25)
                .imported()
                .build();
        assertThat(saleItems)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(expectedSaleItem);
    }

    private ConsoleSaleItemsReader saleItemReader() {
        return new ConsoleSaleItemsReader(new Console(), new SaleItemParser());
    }

    private void writeInput(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream((input + "\n\n").getBytes(UTF_8));
        System.setIn(inputStream);
    }
}