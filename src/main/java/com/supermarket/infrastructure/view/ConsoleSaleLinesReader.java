package com.supermarket.infrastructure.view;

import com.supermarket.domain.sales.ErrorReadingSaleLinesException;
import com.supermarket.domain.sales.SaleLines;
import com.supermarket.domain.sales.SaleLine;

import java.util.ArrayList;
import java.util.List;

public class ConsoleSaleLinesReader implements SaleLines {
    private final Console console;
    private final SaleLineParser saleLineParser;

    public ConsoleSaleLinesReader(Console console, SaleLineParser saleLineParser) {
        this.console = console;
        this.saleLineParser = saleLineParser;
    }

    @Override
    public List<SaleLine> all() {
        console.printLine("Enter sales in the format <qty> <description> at <unit price>\nFor example: 2 books at 13.25\nEntering a blank line completes the sale\n");
        List<SaleLine> saleLines = new ArrayList<>();
        String line = getInput();
        while (isNotEmpty(line)) {
            saleLineParser.parse(line)
                    .ifPresentOrElse(saleLines::add, throwErrorReadingSaleLine);
            line = getInput();
        }
        return saleLines;
    }

    private final Runnable throwErrorReadingSaleLine = () -> {
        throw new ErrorReadingSaleLinesException();
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