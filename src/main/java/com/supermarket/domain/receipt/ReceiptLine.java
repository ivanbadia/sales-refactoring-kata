package com.supermarket.domain.receipt;

import com.supermarket.domain.product.ProductName;

public class ReceiptLine
{
    private ProductName productName;
    private int quantity;
    private double totalAmount;
    private boolean imported;

    public ReceiptLine(int quantity, ProductName productName, double totalAmount, boolean imported) {
        this.productName = productName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.imported = imported;
    }

    public ProductName getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isImported() {
        return imported;
    }
}
