package com.supermarket.domain.sales;

public class SaleLine {
    private String productName;
    private double price;
    private boolean isImported;
    private int quantity;

    public SaleLine(int lineQuantity, String name, double unitPrice, boolean itemIsImported) {
        if (name == null || name.isEmpty()) name = "";

        quantity = lineQuantity;
        productName = name;
        price = unitPrice;
        isImported = itemIsImported;
    }

    public double getTotalAmount() {
        return price * quantity;
    }

    public String getProductName() {
        return productName;
    }

    public boolean isImported() {
        return isImported;
    }

    public int getQuantity() {
        return quantity;
    }
}
