package com.dbtraining.reconx.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Objects;

/**
 * ============================================================================
 * TICKET-ADV020 — FXTrade with Builder pattern
 *
 * WHAT:    FX spot/forward trade with two currencies, a notional in ccy1, and
 *          an fxRate.
 * HOW:     The class uses the same immutable builder pattern as the other
 *          trade types and computes the notional in the quote currency from the
 *          FX rate.
 * WHY:     FX trades have two natural currency sides, and modelling them as
 *          distinct fields makes settlement-side reasoning explicit instead of
 *          burying it in a free-form string.
 * OBSERVE: notional().currency() == ccy2; .amount() == notionalCcy1 * fxRate.
 * ============================================================================
 */
public final class FXTrade implements TradeType {

    private final TradeRef tradeRef;
    private final Currency ccy1;
    private final Currency ccy2;
    private final BigDecimal notionalCcy1;
    private final BigDecimal fxRate;
    private final Side side;
    private final LocalDate tradeDate;
    private final long counterpartyId;

    private FXTrade(Builder b) {
        this.tradeRef       = b.tradeRef;
        this.ccy1           = b.ccy1;
        this.ccy2           = b.ccy2;
        this.notionalCcy1   = b.notionalCcy1;
        this.fxRate         = b.fxRate;
        this.side           = b.side;
        this.tradeDate      = b.tradeDate;
        this.counterpartyId = b.counterpartyId;
    }

    /**
     * Create a new builder for an FX trade.
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
     * Return the trade date for this FX trade.
     *
     * @return the trade date; never {@code null}
     */
    @Override public LocalDate tradeDate()   { return tradeDate; }

    /**
     * Return the asset-class discriminator for this concrete trade type.
     *
     * @return {@link AssetClass#FX}
     */
    @Override public AssetClass assetClass() { return AssetClass.FX; }

    /**
     * Return the notional value in the quote currency.
     *
     * @return the converted notional value; never {@code null}
     * @throws UnsupportedOperationException until the implementation is completed
     */
    @Override public Money notional() {
        // TODO(TICKET-ADV020): return new Money(notionalCcy1 * fxRate, ccy2).
        throw new UnsupportedOperationException("TICKET-ADV020");
    }

    /**
     * Return the first currency in the FX pair.
     *
     * @return the base currency
     */
    public Currency ccy1()           { return ccy1; }

    /**
     * Return the second currency in the FX pair.
     *
     * @return the quote currency
     */
    public Currency ccy2()           { return ccy2; }

    /**
     * Return the notional amount expressed in the first currency.
     *
     * @return the notional amount in ccy1
     */
    public BigDecimal notionalCcy1() { return notionalCcy1; }

    /**
     * Return the FX rate used to convert the notional.
     *
     * @return the rate from ccy1 to ccy2
     */
    public BigDecimal fxRate()       { return fxRate; }

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
     * Compare two FX trades by their trade reference.
     *
     * @param o the object to compare against
     * @return {@code true} when the other object is an FX trade with the same trade reference
     */
    @Override public boolean equals(Object o) {
        return this == o || (o instanceof FXTrade other && Objects.equals(tradeRef, other.tradeRef));
    }

    /**
     * Return a hash code based on the trade reference.
     *
     * @return the hash code for this trade
     */
    @Override public int hashCode() {
        // TODO(TICKET-ADV028): hash from tradeRef.
        throw new UnsupportedOperationException("TICKET-ADV028");
    }

    /**
     * Return a concise string view of the trade for logs.
     *
     * @return a non-PII summary of the trade state
     */
    @Override public String toString() {
        // TODO(TICKET-ADV030): "FXTrade[ref=..., CCY1/CCY2, notional=... CCY1, rate=..., side=...]"
        throw new UnsupportedOperationException("TICKET-ADV030");
    }

    public static final class Builder {
        /**
         * Create an empty builder.
         */
        public Builder() {
        }

        private TradeRef tradeRef;
        private Currency ccy1, ccy2;
        private BigDecimal notionalCcy1, fxRate;
        private Side side;
        private LocalDate tradeDate;
        private long counterpartyId;

        /**
         * Set the trade reference.
         *
         * @param v the trade reference to assign
         * @return this builder for fluent chaining
         */
        public Builder tradeRef(TradeRef v)        { this.tradeRef = v; return this; }

        /**
         * Set the first currency in the FX pair.
         *
         * @param code the ISO currency code to assign to {@code ccy1}
         * @return this builder for fluent chaining
         */
        public Builder ccy1(String code)           { this.ccy1 = Currency.getInstance(code); return this; }

        /**
         * Set the second currency in the FX pair.
         *
         * @param code the ISO currency code to assign to {@code ccy2}
         * @return this builder for fluent chaining
         */
        public Builder ccy2(String code)           { this.ccy2 = Currency.getInstance(code); return this; }

        /**
         * Set the notional amount in the first currency.
         *
         * @param v the notional amount to assign
         * @return this builder for fluent chaining
         */
        public Builder notionalCcy1(BigDecimal v)  { this.notionalCcy1 = v; return this; }

        /**
         * Set the FX rate used to convert the notional.
         *
         * @param v the rate to assign
         * @return this builder for fluent chaining
         */
        public Builder fxRate(BigDecimal v)        { this.fxRate = v; return this; }

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
         * @return a validated FX trade instance
         * @throws NullPointerException if any required field ({@code tradeRef},
         *         {@code ccy1}, {@code ccy2}, {@code notionalCcy1},
         *         {@code fxRate}, {@code side}, or {@code tradeDate}) is not set
         * @throws IllegalStateException if {@code ccy1} and {@code ccy2} are the
         *         same currency or if {@code fxRate} is not strictly positive
         */
        public FXTrade build() {
            Objects.requireNonNull(tradeRef, "tradeRef");
            Objects.requireNonNull(ccy1, "ccy1");
            Objects.requireNonNull(ccy2, "ccy2");
            Objects.requireNonNull(notionalCcy1, "notionalCcy1");
            Objects.requireNonNull(fxRate, "fxRate");
            Objects.requireNonNull(side, "side");
            Objects.requireNonNull(tradeDate, "tradeDate");

            if (ccy1.equals(ccy2)) {
                throw new IllegalStateException("ccy1 and ccy2 must differ");
            }
            if (fxRate.signum() <= 0) {
                throw new IllegalStateException("fxRate must be > 0");
            }
            return new FXTrade(this);
        }
    }
}
