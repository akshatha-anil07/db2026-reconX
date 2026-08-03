package com.dbtraining.reconx.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Objects;

/**
 * ============================================================================
 * TICKET-ADV021 — BondTrade with Builder pattern
 *
 * WHAT:    Fixed-income trade carrying a coupon, a maturity date, a face value,
 *          and an ISIN.
 * HOW:     The class uses an immutable builder and exposes the bond-specific
 *          attributes through accessors while keeping the notional equal to the
 *          face value in the bond currency.
 * WHY:     Bonds need coupon and maturity information to support cash-flow
 *          modelling, and putting those fields on the trade object keeps the
 *          reconciliation model simple.
 * ============================================================================
 */
public final class BondTrade implements TradeType {

    private final TradeRef tradeRef;
    private final String isin;
    private final BigDecimal faceValue;
    private final BigDecimal couponRate;
    private final LocalDate maturityDate;
    private final Currency currency;
    private final Side side;
    private final LocalDate tradeDate;
    private final long counterpartyId;

    private BondTrade(Builder b) {
        this.tradeRef       = b.tradeRef;
        this.isin           = b.isin;
        this.faceValue      = b.faceValue;
        this.couponRate     = b.couponRate;
        this.maturityDate   = b.maturityDate;
        this.currency       = b.currency;
        this.side           = b.side;
        this.tradeDate      = b.tradeDate;
        this.counterpartyId = b.counterpartyId;
    }

    /**
     * Create a new builder for a bond trade.
     *
     * @return a fresh builder instance
     */
    public static Builder builder() { return new Builder(); }

    /**
     * Return the immutable trade reference for this trade.
     *
     * @return the trade reference; never {@code null}
     */
    @Override public TradeRef tradeRef()     { return tradeRef; }

    /**
     * Return the trade date for this bond trade.
     *
     * @return the trade date; never {@code null}
     */
    @Override public LocalDate tradeDate()   { return tradeDate; }

    /**
     * Return the asset-class discriminator for this concrete trade type.
     *
     * @return {@link AssetClass#BOND}
     */
    @Override public AssetClass assetClass() { return AssetClass.BOND; }

    /**
     * Return the notional value of the bond in its settlement currency.
     *
     * @return the face value expressed as a monetary value; never {@code null}
     * @throws UnsupportedOperationException until the implementation is completed
     */
    @Override public Money notional() {
        // TODO(TICKET-ADV021): return new Money(faceValue, currency).
        return new Money(faceValue, currency);
    }

    /**
     * Return the ISIN for this bond.
     *
     * @return the instrument identifier
     */
    public String isin()              { return isin; }

    /**
     * Return the bond face value.
     *
     * @return the face value of the bond
     */
    public BigDecimal faceValue()     { return faceValue; }

    /**
     * Return the coupon rate for the bond.
     *
     * @return the coupon rate as a decimal percentage or rate
     */
    public BigDecimal couponRate()    { return couponRate; }

    /**
     * Return the maturity date for the bond.
     *
     * @return the maturity date of the bond
     */
    public LocalDate maturityDate()   { return maturityDate; }

    /**
     * Return the settlement currency for the bond.
     *
     * @return the currency attached to the bond
     */
    public Currency currency()        { return currency; }

    /**
     * Return the trade side.
     *
     * @return the buy or sell side for the trade
     */
    public Side side()                { return side; }

    /**
     * Return the counterparty identifier associated with the trade.
     *
     * @return the counterparty identifier
     */
    public long counterpartyId()      { return counterpartyId; }

    /**
     * Compare two bond trades by their trade reference.
     *
     * @param o the object to compare against
     * @return {@code true} when the other object is a bond trade with the same trade reference
     */
    @Override public boolean equals(Object o) {
        return this == o || (o instanceof BondTrade other && Objects.equals(tradeRef, other.tradeRef));
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
    @Override public String toString() {
        // TODO(TICKET-ADV030): "BondTrade[ref=..., isin=..., face=... CCY, coupon=..., maturity=..., side=...]"
        return "BondTrade[ref=%s, isin=%s, face=%s %s, coupon=%s, maturity=%s, side=%s]".formatted(tradeRef, isin, faceValue.toPlainString(), currency.getCurrencyCode(),couponRate, maturityDate, side);
    }

    public static final class Builder {
        /**
         * Create an empty builder.
         */
        public Builder() {
        }

        private TradeRef tradeRef;
        private String isin;
        private BigDecimal faceValue, couponRate;
        private LocalDate maturityDate, tradeDate;
        private Currency currency;
        private Side side;
        private long counterpartyId;

        /**
         * Set the trade reference.
         *
         * @param v the trade reference to assign
         * @return this builder for fluent chaining
         */
        public Builder tradeRef(TradeRef v)        { this.tradeRef = v; return this; }

        /**
         * Set the ISIN.
         *
         * @param v the ISIN to assign
         * @return this builder for fluent chaining
         */
        public Builder isin(String v)              { this.isin = v; return this; }

        /**
         * Set the face value.
         *
         * @param v the face value to assign
         * @return this builder for fluent chaining
         */
        public Builder faceValue(BigDecimal v)     { this.faceValue = v; return this; }

        /**
         * Set the coupon rate.
         *
         * @param v the coupon rate to assign
         * @return this builder for fluent chaining
         */
        public Builder couponRate(BigDecimal v)    { this.couponRate = v; return this; }

        /**
         * Set the maturity date.
         *
         * @param v the maturity date to assign
         * @return this builder for fluent chaining
         */
        public Builder maturityDate(LocalDate v)   { this.maturityDate = v; return this; }

        /**
         * Set the settlement currency by code.
         *
         * @param code the ISO currency code to assign
         * @return this builder for fluent chaining
         */
        public Builder currency(String code)       { this.currency = Currency.getInstance(code); return this; }

        /**
         * Set the trade side.
         *
         * @param v the side to assign
         * @return this builder for fluent chaining
         */
        public Builder side(Side v)                { this.side = v; return this; }

        /**
         * Set the trade date.
         *
         * @param v the trade date to assign
         * @return this builder for fluent chaining
         */
        public Builder tradeDate(LocalDate v)      { this.tradeDate = v; return this; }

        /**
         * Set the counterparty identifier.
         *
         * @param v the counterparty identifier to assign
         * @return this builder for fluent chaining
         */
        public Builder counterpartyId(long v)      { this.counterpartyId = v; return this; }

        /**
         * Build the immutable trade after validating all required fields.
         *
         * @return a validated bond trade instance
         * @throws NullPointerException if any required field ({@code tradeRef},
         *         {@code isin}, {@code faceValue}, {@code couponRate},
         *         {@code maturityDate}, {@code currency}, {@code side}, or
         *         {@code tradeDate}) is not set
         * @throws IllegalStateException if {@code maturityDate} is before
         *         {@code tradeDate}
         */
        public BondTrade build() {
            Objects.requireNonNull(tradeRef, "tradeRef");
            Objects.requireNonNull(isin, "isin");
            Objects.requireNonNull(faceValue, "faceValue");
            Objects.requireNonNull(couponRate, "couponRate");
            Objects.requireNonNull(maturityDate, "maturityDate");
            Objects.requireNonNull(currency, "currency");
            Objects.requireNonNull(side, "side");
            Objects.requireNonNull(tradeDate, "tradeDate");

            if (maturityDate.isBefore(tradeDate)) {
                throw new IllegalStateException("maturityDate cannot be before tradeDate");
            }
            return new BondTrade(this);
        }
    }
}
