package com.supermarket.infrastructure.view;

import com.supermarket.domain.receipt.Receipt;

public interface Output {
    void display(Receipt receipt);
    void error(String errorMessage);
}
