package com.supermarket.domain.sales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleSaleLineReader {
    public final BufferedReader bufferedReader;

    public ConsoleSaleLineReader() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String getInput() {
        String result;
        System.out.println("Sale : ");

        result = readLine();

        if (!"".equals(result))
            result = result.trim();
        return result;
    }

    public String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            return "";
        }
    }
}