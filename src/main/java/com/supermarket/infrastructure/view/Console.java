package com.supermarket.infrastructure.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
    private final BufferedReader bufferedReader;

    public Console() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    public void printLine(String message) {
        System.out.println(message);
    }
}