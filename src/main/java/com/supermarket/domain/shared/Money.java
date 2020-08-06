package com.supermarket.domain.shared;

public class Money {
    public static final Money ZERO = new Money(0);
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
