package com.supermarket.infrastructure.input;

import com.supermarket.domain.sales.SaleLineNotValid;
import com.supermarket.domain.sales.SaleLinesReader;
import com.supermarket.domain.sales.SaleLine;
import io.vavr.control.Either;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsoleSaleLinesReader implements SaleLinesReader {
    private final Console console;
    private final SaleLineParser saleLineParser;

    public ConsoleSaleLinesReader(Console console, SaleLineParser saleLineParser) {
        this.console = console;
        this.saleLineParser = saleLineParser;
    }

    @Override
    public List<SaleLine> read() {
        console.printLine("Enter sales in the format <qty> <description> at <unit price>\nFor example: 2 books at 13.25\nEntering a blank line completes the sale\n");
        List<SaleLine> saleLines = new ArrayList<>();
        String line = getInput();
        while (isNotEmpty(line)) {
            saleLineParser.parse(line)
                    .ifPresentOrElse(saleLines::add, () -> { throw new SaleLineNotValid(); });
            line = getInput();
        }
        return saleLines;
    }

    private String getInput() {
        console.printLine("Sale : ");
        String result = console.readLine();
        return isNotEmpty(result) ? result.trim() : result;
    }

    private boolean isNotEmpty(String line) {
        return line != null && !"".equals(line);
    }
}