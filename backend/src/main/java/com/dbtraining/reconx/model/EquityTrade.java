package com.dbtraining.reconx.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Objects;

/**
 * ============================================================================
 * TICKET-ADV019 — EquityTrade with Builder pattern
 *
 * WHAT:    Concrete trade type for equity (cash-share) trades.
 * HOW:     The class is immutable and constructed through a nested builder that
 *          validates the required fields in {@link Builder#build()} before the
 *          instance is created.
 * WHY:     Eight required fields on a single constructor would be hard to read
 *          and easy to misuse at the call site. The builder gives named
 *          arguments, keeps the object immutable, and centralises the validity
 *          checks in one place.
 * OBSERVE: Calling build() with a missing required field throws
 *          IllegalStateException — verified by EquityTradeTest.
 * HINT:    The same shape is applied to FXTrade, BondTrade, and DerivativeTrade.
 * ============================================================================
 *
 * TICKET-ADV028 — equals/hashCode from tradeRef (Object methods on a regular class)
 * TICKET-ADV030 — toString() omits PII, prints reference/symbol/qty/price/side
 */
public final class EquityTrade implements TradeType {

    private final TradeRef tradeRef;
    private final String instrumentSymbol;
    private final BigDecimal quantity;
    private final BigDecimal price;
    private final Currency currency;
    private final Side side;
    private final LocalDate tradeDate;
    private final long counterpartyId;

    private EquityTrade(Builder b) {
        this.tradeRef         = b.tradeRef;
        this.instrumentSymbol = b.instrumentSymbol;
        this.quantity         = b.quantity;
        this.price            = b.price;
        this.currency         = b.currency;
        this.side             = b.side;
        this.tradeDate        = b.tradeDate;
        this.counterpartyId   = b.counterpartyId;
    }

    /**
     * Create a new builder for an equity trade.
     *
     * @return a fresh builder instance
     */
    public static Builder builder() { return new Builder(); }

    /**
     * Return the immutable trade reference for this trade.
     *
     * @return the trade reference; never {@code null}
     */
    @Override public TradeRef tradeRef()    { return tradeRef; }

    /**
     * Return the trade date for this equity trade.
     *
     * @return the trade date; never {@code null}
     */
    @Override public LocalDate tradeDate()  { return tradeDate; }

    /**
     * Return the asset-class discriminator for this concrete trade type.
     *
     * @return {@link AssetClass#EQUITY}
     */
    @Override public AssetClass assetClass(){ return AssetClass.EQUITY; }

    /**
     * Return the notional value of the trade in its settlement currency.
     *
     * @return the quantity multiplied by the price, expressed as a monetary value
     */
    @Override public Money notional() {
        return new Money(quantity.multiply(price), currency);
    }

    /**
     * Return the instrument symbol for this trade.
     *
     * @return the ticker or symbol for the equity instrument
     */
    public String instrumentSymbol() { return instrumentSymbol; }

    /**
     * Return the traded quantity.
     *
     * @return the quantity as a decimal amount
     */
    public BigDecimal quantity()     { return quantity; }

    /**
     * Return the unit price for the trade.
     *
     * @return the price per unit
     */
    public BigDecimal price()        { return price; }

    /**
     * Return the settlement currency for the trade.
     *
     * @return the currency used for the trade value
     */
    public Currency currency()       { return currency; }

    /**
     * Return the trade side.
     *
     * @return the buy or sell side for the trade
     */
    public Side side()               { return side; }

    /**
     * Return the counterparty identifier associated with the trade.
     *
     * @return the counterparty identifier
     */
    public long counterpartyId()     { return counterpartyId; }

    /**
     * Compare two equity trades by their trade reference.
     *
     * @param o the object to compare against
     * @return {@code true} when the other object is an equity trade with the same trade reference
     */
    @Override
    public boolean equals(Object o) {
        return this == o || (o instanceof EquityTrade other && Objects.equals(tradeRef, other.tradeRef));
    }

    /**
     * Return a hash code based on the trade reference.
     *
     * @return the hash code for this trade
     */
    @Override public int hashCode() {
        return Objects.hash(tradeRef);
    }

    /**
     * Return a concise string view of the trade for logs.
     *
     * @return a non-PII summary of the trade state
     */
    @Override
    public String toString() {
        return "EquityTrade[ref=%s, symbol=%s, qty=%s, price=%s %s, side=%s]".formatted(tradeRef, instrumentSymbol, quantity, price.toPlainString(), currency.getCurrencyCode(), side);
    }

    /**
     * Fluent builder for an immutable {@link EquityTrade}.
     */
    public static final class Builder {
        /**
         * Create an empty builder.
         */
        public Builder() {
        }

        private TradeRef tradeRef;
        private String instrumentSymbol;
        private BigDecimal quantity;
        private BigDecimal price;
        private Currency currency;
        private Side side;
        private LocalDate tradeDate;
        private long counterpartyId;

        /**
         * Set the trade reference.
         *
         * @param v the trade reference to assign
         * @return this builder for fluent chaining
         */
        public Builder tradeRef(TradeRef v)           { this.tradeRef = v;        return this; }

        /**
         * Set the instrument symbol.
         *
         * @param v the symbol to assign
         * @return this builder for fluent chaining
         */
        public Builder instrumentSymbol(String v)     { this.instrumentSymbol = v; return this; }

        /**
         * Set the traded quantity.
         *
         * @param v the quantity to assign
         * @return this builder for fluent chaining
         */
        public Builder quantity(BigDecimal v)         { this.quantity = v;        return this; }

        /**
         * Set the unit price.
         *
         * @param v the price to assign
         * @return this builder for fluent chaining
         */
        public Builder price(BigDecimal v)            { this.price = v;           return this; }

        /**
         * Set the settlement currency.
         *
         * @param v the currency to assign
         * @return this builder for fluent chaining
         */
        public Builder currency(Currency v)           { this.currency = v;        return this; }

        /**
         * Set the settlement currency by code.
         *
         * @param code the ISO currency code to assign
         * @return this builder for fluent chaining
         */
        public Builder currency(String code)          { return currency(Currency.getInstance(code)); }

        /**
         * Set the trade side.
         *
         * @param v the side to assign
         * @return this builder for fluent chaining
         */
        public Builder side(Side v)                   { this.side = v;            return this; }

        /**
         * Set the trade date.
         *
         * @param v the trade date to assign
         * @return this builder for fluent chaining
         */
        public Builder tradeDate(LocalDate v)         { this.tradeDate = v;       return this; }

        /**
         * Set the counterparty identifier.
         *
         * @param v the counterparty identifier to assign
         * @return this builder for fluent chaining
         */
        public Builder counterpartyId(long v)         { this.counterpartyId = v;  return this; }

        /**
         * Build the immutable trade after validating all required fields.
         *
         * @return a validated equity trade instance
         * @throws NullPointerException if any required field ({@code tradeRef},
         *         {@code instrumentSymbol}, {@code quantity}, {@code price},
         *         {@code currency}, {@code side}, or {@code tradeDate}) is not set
         * @throws IllegalStateException if {@code quantity} or {@code price} is not
         *         strictly positive
         */
        public EquityTrade build() {
            Objects.requireNonNull(tradeRef, "tradeRef");
            Objects.requireNonNull(instrumentSymbol, "instrumentSymbol");
            Objects.requireNonNull(quantity, "quantity");
            Objects.requireNonNull(price, "price");
            Objects.requireNonNull(currency, "currency");
            Objects.requireNonNull(side, "side");
            Objects.requireNonNull(tradeDate, "tradeDate");

            if (quantity.signum() <= 0 || price.signum() <= 0) {
                throw new IllegalStateException("quantity and price must be greater than zero");
            }

            return new EquityTrade(this);
        }
    }
}
