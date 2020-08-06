package com.supermarket.domain.shared;

public class Money {
    private final double value;

    public Money(double value) {
        this.value = value;
    }

    public static Money sum(Money money, Money money2) {
        return new Money(money.asDouble() + money2.asDouble());
    }

    public double asDouble() {
        return value;
    }
}
