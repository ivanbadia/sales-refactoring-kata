package com.supermarket.domain.receipt;

import com.supermarket.domain.product.ProductName;

public class ReceiptLine
{
    private final int quantity;
    private final ProductName productName;
    private final double totalAmount;
    private final boolean imported;

    public ReceiptLine(int quantity, ProductName productName, double totalAmount, boolean imported) {
        this.quantity = quantity;
        this.productName = productName;
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
