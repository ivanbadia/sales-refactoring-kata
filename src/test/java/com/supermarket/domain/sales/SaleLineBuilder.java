package com.supermarket.domain.sales;

import com.supermarket.domain.product.ProductName;
import com.supermarket.domain.shared.Money;

public class SaleLineBuilder {

    private int quantity = 1;
    private ProductName name = new ProductName("book");
    private Money unitPrice = new Money(12.49);
    private boolean isImported = false;

    public static SaleLineBuilder aSaleLine() {
        return new SaleLineBuilder();
    }


    public SaleLineBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public SaleLineBuilder withName(ProductName name) {
        this.name = name;
        return this;
    }

    public SaleLineBuilder withUnitPrice(double unitPrice) {
        this.unitPrice = new Money(unitPrice);
        return this;
    }

    public SaleLineBuilder imported() {
        isImported = true;
        return this;
    }

    public SaleLine build() {
        return new SaleLine(quantity, name, unitPrice, isImported);
    }
}
