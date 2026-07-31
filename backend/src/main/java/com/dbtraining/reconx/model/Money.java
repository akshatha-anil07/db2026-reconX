package com.dbtraining.reconx.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

/**
 * ============================================================================
 * TICKET-ADV024 — Immutable value object: Money
 *
 * WHAT:    Record bundling a {@link BigDecimal} amount with a {@link Currency}.
 *          Used everywhere a monetary value crosses a boundary (DTO, event,
 *          metric).
 * HOW:     The compact constructor enforces non-null amount and currency plus
 *          a non-negative amount, while the value type preserves the currency
 *          context across arithmetic operations.
 * WHY:     Passing raw BigDecimal around loses currency context — a USD 100
 *          can be silently added to a EUR 100. Money makes the mismatch fail
 *          at the type level and keeps the arithmetic precise.
 * OBSERVE: {@code Money.of("100.00","USD").plus(Money.of("50","EUR"))} throws.
 *          {@code Money.of("100","USD").plus(Money.of("50","USD"))} returns 150 USD.
 * ============================================================================
 *
 * @param amount the decimal amount to store; must be non-negative
 * @param currency the currency for the amount; must not be {@code null}
 */
public record Money(BigDecimal amount, Currency currency) {

    /**
     * Create a validated monetary value.
     *
     * @param amount the decimal amount to store; must be non-negative
     * @param currency the currency for the amount; must not be {@code null}
     * @throws NullPointerException if either argument is {@code null}
     * @throws IllegalArgumentException if {@code amount} is negative
     */
    public Money {
        Objects.requireNonNull(amount, "amount");
        Objects.requireNonNull(currency, "currency");
        if (amount.signum() < 0) {
            throw new IllegalArgumentException("Money amount cannot be negative: " + amount);
        }
    }

    /**
     * Create money from a textual amount and currency code.
     *
     * @param amount the decimal amount expressed as a string
     * @param currencyCode the three-letter ISO currency code
     * @return a validated monetary value instance
     * @throws NullPointerException if either input is {@code null}
     * @throws NumberFormatException if the amount cannot be parsed as a {@link BigDecimal}
     * @throws IllegalArgumentException if the currency code is invalid
     */
    public static Money of(String amount, String currencyCode) {
        return new Money(new BigDecimal(amount), Currency.getInstance(currencyCode));
    }

    /**
     * Create money from a {@link BigDecimal} amount and a currency code.
     *
     * @param amount the decimal amount to store
     * @param currencyCode the three-letter ISO currency code
     * @return a validated monetary value instance
     * @throws NullPointerException if either input is {@code null}
     * @throws IllegalArgumentException if the currency code is invalid
     */
    public static Money of(BigDecimal amount, String currencyCode) {
        return new Money(amount, Currency.getInstance(currencyCode));
    }

    /**
     * Add another monetary amount of the same currency.
     *
     * @param other the monetary value to add; it must use the same currency as this value
     * @return a new monetary value containing the summed amount
     * @throws NullPointerException if {@code other} is {@code null}
     * @throws IllegalArgumentException if {@code other.currency} differs from this currency
     * @throws UnsupportedOperationException until the implementation is completed
     */
    public Money plus(Money other) {
        // TODO(TICKET-ADV024): validate same currency, then return a new Money
        //                     whose amount = this.amount + other.amount.
        throw new UnsupportedOperationException("TICKET-ADV024");
    }

    /**
     * Multiply this monetary amount by a scalar factor.
     *
     * @param multiplier the factor to apply to the amount
     * @return a new monetary value containing the scaled amount
     * @throws NullPointerException if {@code multiplier} is {@code null}
     * @throws UnsupportedOperationException until the implementation is completed
     */
    public Money times(BigDecimal multiplier) {
        // TODO(TICKET-ADV024): return a new Money whose amount = this.amount * multiplier.
        throw new UnsupportedOperationException("TICKET-ADV024");
    }
}
