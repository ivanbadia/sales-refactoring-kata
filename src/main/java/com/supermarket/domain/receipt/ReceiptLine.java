package com.supermarket.domain.receipt;

import com.supermarket.domain.product.ProductName;
import com.supermarket.domain.shared.Money;

public class ReceiptLine
{
    private final int quantity;
    private final ProductName productName;
    private final Money totalAmount;
    private final boolean imported;

    public ReceiptLine(int quantity, ProductName productName, Money totalAmount, boolean imported) {
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

    public Money getTotalAmount() {
        return totalAmount;
    }

    public boolean isImported() {
        return imported;
    }
}
