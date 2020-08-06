package com.supermarket.infrastructure.view;

import com.supermarket.domain.sales.ErrorReadingSaleItemsException;
import com.supermarket.domain.sales.SaleItems;
import com.supermarket.domain.sales.SaleItem;

import java.util.ArrayList;
import java.util.List;

public class ConsoleSaleItemsReader implements SaleItems {
    private final Console console;
    private final SaleItemParser saleItemParser;

    public ConsoleSaleItemsReader(Console console, SaleItemParser saleItemParser) {
        this.console = console;
        this.saleItemParser = saleItemParser;
    }

    @Override
    public List<SaleItem> all() {
        console.printLine("Enter sales in the format <qty> <description> at <unit price>\nFor example: 2 books at 13.25\nEntering a blank line completes the sale\n");
        List<SaleItem> saleItems = new ArrayList<>();
        String line = getInput();
        while (isNotEmpty(line)) {
            saleItemParser.parse(line)
                    .ifPresentOrElse(saleItems::add, throwErrorReadingSaleItem);
            line = getInput();
        }
        return saleItems;
    }

    private final Runnable throwErrorReadingSaleItem = () -> {
        throw new ErrorReadingSaleItemsException();
    };

    private String getInput() {
        console.printLine("Sale : ");
        String result = console.readLine();
        return isNotEmpty(result) ? result.trim() : result;
    }

    private boolean isNotEmpty(String line) {
        return line != null && !"".equals(line);
    }
}