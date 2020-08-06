package com.supermarket.domain.sales;

import com.supermarket.domain.product.ProductName;
import com.supermarket.domain.shared.Money;

public class SaleItem {
    private final ProductName productName;
    private final Money unitPrice;
    private final boolean isImported;
    private final int quantity;

    public SaleItem(int quantity, ProductName name, Money unitPrice, boolean isImported) {
        this.quantity = quantity;
        this.productName = name;
        this.unitPrice = unitPrice;
        this.isImported = isImported;
    }

    public Money getTotalAmount() {
        return new Money(unitPrice.asDouble() * quantity);
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
