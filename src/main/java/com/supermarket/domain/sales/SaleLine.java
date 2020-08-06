package com.supermarket.domain.sales;

import com.supermarket.domain.product.ProductName;

public class SaleLine {
    private ProductName productName;
    private double price;
    private boolean isImported;
    private int quantity;

    public SaleLine(int lineQuantity, String name, double unitPrice, boolean itemIsImported) {
        //TODO A STRING
        if (name == null || name.isEmpty()) name = "";

        quantity = lineQuantity;
        productName = new ProductName(name);
        price = unitPrice;
        isImported = itemIsImported;
    }

    public double getTotalAmount() {
        return price * quantity;
    }

    public ProductName getProductName() {
        return productName;
    }

    public boolean isImported() {
        return isImported;
    }

    public int getQuantity() {
        return quantity;
    }
}
