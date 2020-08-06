package com.supermarket.domain.sales;

import com.supermarket.domain.product.ProductName;
import com.supermarket.domain.shared.Money;

public class SaleItemBuilder {

    private int quantity = 1;
    private ProductName name = new ProductName("book");
    private Money unitPrice = new Money(12.49);
    private boolean isImported = false;

    public static SaleItemBuilder aSaleItem() {
        return new SaleItemBuilder();
    }


    public SaleItemBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public SaleItemBuilder withName(ProductName name) {
        this.name = name;
        return this;
    }

    public SaleItemBuilder withUnitPrice(double unitPrice) {
        this.unitPrice = new Money(unitPrice);
        return this;
    }

    public SaleItemBuilder imported() {
        isImported = true;
        return this;
    }

    public SaleItem build() {
        return new SaleItem(quantity, name, unitPrice, isImported);
    }
}
