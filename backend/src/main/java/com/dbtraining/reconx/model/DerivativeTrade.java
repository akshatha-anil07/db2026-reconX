package com.dbtraining.reconx.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Objects;

/**
 * ============================================================================
 * TICKET-ADV022 — DerivativeTrade with Builder pattern
 *
 * WHAT:    Option-style derivative trade with an underlying, a strike, an
 *          expiry date, and an option type.
 * HOW:     The class uses the same immutable builder pattern as the other
 *          trade types and exposes the derivative-specific attributes through
 *          accessors.
 * WHY:     Derivatives need their own payoff information and expiry semantics,
 *          so keeping those fields on the trade model makes the downstream
 *          reconciliation and reporting logic straightforward.
 * ============================================================================
 */
public final class DerivativeTrade implements TradeType {

    /**
     * ============================================================================
     * TICKET-ADV031 — Supported option styles for derivative trades
     *
     * WHAT:    Enumerates the two option styles supported by the derivative
     *          model.
     * HOW:     The enum is attached to the trade object so callers can pass a
     *          strongly typed option style instead of a free-form string.
     * WHY:     The reconciliation and pricing layers need a closed vocabulary
     *          for option direction so unsupported values cannot sneak in.
     * ============================================================================
     */
    public enum OptionType {
        /**
         * A call option.
         */
        CALL,
        /**
         * A put option.
         */
        PUT
    }

    private final TradeRef tradeRef;
    private final String underlying;
    private final BigDecimal strike;
    private final BigDecimal quantity;
    private final LocalDate expiry;
    private final OptionType optionType;
    private final Currency currency;
    private final Side side;
    private final LocalDate tradeDate;
    private final long counterpartyId;

    private DerivativeTrade(Builder b) {
        this.tradeRef       = b.tradeRef;
        this.underlying     = b.underlying;
        this.strike         = b.strike;
        this.quantity       = b.quantity;
        this.expiry         = b.expiry;
        this.optionType     = b.optionType;
        this.currency       = b.currency;
        this.side           = b.side;
        this.tradeDate      = b.tradeDate;
        this.counterpartyId = b.counterpartyId;
    }

    /**
     * Create a new builder for a derivative trade.
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
     * Return the trade date for this derivative trade.
     *
     * @return the trade date; never {@code null}
     */
    @Override public LocalDate tradeDate()   { return tradeDate; }

    /**
     * Return the asset-class discriminator for this concrete trade type.
     *
     * @return {@link AssetClass#DERIVATIVE}
     */
    @Override public AssetClass assetClass() { return AssetClass.DERIVATIVE; }

    /**
     * Return the simplified notional for this derivative.
     *
     * @return the strike multiplied by the quantity in the trade currency
     * @throws UnsupportedOperationException until the implementation is completed
     */
    @Override public Money notional() {
        // TODO(TICKET-ADV022): return new Money(strike * quantity, currency).
        throw new UnsupportedOperationException("TICKET-ADV022");
    }

    /**
     * Return the underlying instrument for this derivative.
     *
     * @return the underlying symbol or name
     */
    public String underlying()       { return underlying; }

    /**
     * Return the strike price for the derivative.
     *
     * @return the strike price
     */
    public BigDecimal strike()       { return strike; }

    /**
     * Return the quantity for the derivative.
     *
     * @return the derivative quantity
     */
    public BigDecimal quantity()     { return quantity; }

    /**
     * Return the expiry date for the derivative.
     *
     * @return the expiry date
     */
    public LocalDate expiry()        { return expiry; }

    /**
     * Return the option style.
     *
     * @return the option type for the trade
     */
    public OptionType optionType()   { return optionType; }

    /**
     * Return the settlement currency for the derivative.
     *
     * @return the currency attached to the trade
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
     * Compare two derivative trades by their trade reference.
     *
     * @param o the object to compare against
     * @return {@code true} when the other object is a derivative trade with the same trade reference
     */
    @Override public boolean equals(Object o) {
        return this == o || (o instanceof DerivativeTrade other && Objects.equals(tradeRef, other.tradeRef));
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
        // TODO(TICKET-ADV030): "DerivativeTrade[ref=..., TYPE UNDERLYING on date, strike=... CCY, qty=..., expiry=..., side=...]"
        return "DerivativeTrade[ref=%s, %s %s on %s, strike=%s %s, qty=%s, expiry=%s, side=%s]".formatted(tradeRef, optionType, underlying, tradeDate, strike, currency.getCurrencyCode(), quantity, expiry, side);
    }

    public static final class Builder {
        /**
         * Create an empty builder.
         */
        public Builder() {
        }

        private TradeRef tradeRef;
        private String underlying;
        private BigDecimal strike, quantity;
        private LocalDate expiry, tradeDate;
        private OptionType optionType;
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
         * Set the underlying instrument.
         *
         * @param v the underlying to assign
         * @return this builder for fluent chaining
         */
        public Builder underlying(String v)        { this.underlying = v; return this; }

        /**
         * Set the strike price.
         *
         * @param v the strike price to assign
         * @return this builder for fluent chaining
         */
        public Builder strike(BigDecimal v)        { this.strike = v; return this; }

        /**
         * Set the trade quantity.
         *
         * @param v the quantity to assign
         * @return this builder for fluent chaining
         */
        public Builder quantity(BigDecimal v)      { this.quantity = v; return this; }

        /**
         * Set the expiry date.
         *
         * @param v the expiry date to assign
         * @return this builder for fluent chaining
         */
        public Builder expiry(LocalDate v)         { this.expiry = v; return this; }

        /**
         * Set the option type.
         *
         * @param v the option type to assign
         * @return this builder for fluent chaining
         */
        public Builder optionType(OptionType v)    { this.optionType = v; return this; }

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
         * @return a validated derivative trade instance
         * @throws NullPointerException if any required field ({@code tradeRef},
         *         {@code underlying}, {@code strike}, {@code quantity},
         *         {@code expiry}, {@code optionType}, {@code currency},
         *         {@code side}, or {@code tradeDate}) is not set
         * @throws IllegalStateException if {@code strike} or {@code quantity}
         *         is not strictly positive or if {@code expiry} is before
         *         {@code tradeDate}
         */
        public DerivativeTrade build() {
            Objects.requireNonNull(tradeRef, "tradeRef");
            Objects.requireNonNull(underlying, "underlying");
            Objects.requireNonNull(strike, "strike");
            Objects.requireNonNull(quantity, "quantity");
            Objects.requireNonNull(expiry, "expiry");
            Objects.requireNonNull(optionType, "optionType");
            Objects.requireNonNull(currency, "currency");
            Objects.requireNonNull(side, "side");
            Objects.requireNonNull(tradeDate, "tradeDate");

            if (strike.signum() <= 0) {
                throw new IllegalStateException("strike must be > 0");
            }
            if (quantity.signum() <= 0) {
                throw new IllegalStateException("quantity must be > 0");
            }
            if (expiry.isBefore(tradeDate)) {
                throw new IllegalStateException("expiry cannot be before tradeDate");
            }
            return new DerivativeTrade(this);
        }
    }
}
