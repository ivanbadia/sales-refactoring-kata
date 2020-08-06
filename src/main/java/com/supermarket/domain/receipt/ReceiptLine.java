package com.supermarket.domain.receipt;

import com.supermarket.domain.product.ProductName;

public class ReceiptLine
{
    private ProductName productName;
    private int quantity;
    private double amount;

    public ReceiptLine(int quantity, ProductName productName, double amount) {
        this.productName = productName;
        this.quantity = quantity;
        this.amount = amount;
    }

    public ProductName getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmount() {
        return amount;
    }
}
