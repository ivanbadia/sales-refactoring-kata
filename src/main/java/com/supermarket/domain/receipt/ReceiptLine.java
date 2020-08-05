package com.supermarket.domain.receipt;

public class ReceiptLine
{
    private String productName;
    private int quantity;
    private double amount;

    public ReceiptLine(int quantity, String productName, double amount) {
        this.productName = productName;
        this.quantity = quantity;
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmount() {
        return amount;
    }
}
