package com.dbtraining.reconx.model;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * ============================================================================
 * TICKET-ADV018 — Sealed interface TradeType
 *
 * WHAT:    Sealed root of the trade hierarchy. Only the four named
 *          permitted classes can implement it. Any new asset class needs an
 *          explicit code change here — by design.
 * HOW:     The interface is sealed and explicitly permits the four concrete
 *          trade implementations that the reconciliation engine understands.
 * WHY:     Sealing prevents a stray implementation from bypassing the
 *          pattern-matching logic used by the reconciliation service and turns
 *          the switch into a compiler-enforced exhaustive case analysis.
 * OBSERVE: Removing one of the permitted implementations causes a compile
 *          error in the reconciliation engine's switch expression.
 * HINT:    See Day 2 trainer guide §"Sprint 1A — sealed hierarchy" for the
 *          design discussion.
 * ============================================================================
 *
 * TICKET-ADV027 — Comparable natural ordering (most-recent trade first)
 * TICKET-ADV028 — equals/hashCode based on tradeRef (the natural key)
 *
 * Comparator lives on the sealed interface, so every implementation shares the
 * same ordering rule and there is no per-class comparison override to forget
 * when adding a new field.
 */
public sealed interface TradeType
        extends Comparable<TradeType>
        permits EquityTrade, FXTrade, BondTrade, DerivativeTrade {

    /**
     * Return the immutable trade reference that uniquely identifies this trade.
     *
     * @return the natural key for this trade; never {@code null}
     */
    TradeRef tradeRef();

    /**
     * Return the monetary notional for this trade in the trade currency.
     *
     * @return the notional value for reconciliation and reporting; never {@code null}
     */
    Money notional();

    /**
     * Return the business date on which the trade was struck.
     *
     * @return the trade date; never {@code null}
     */
    LocalDate tradeDate();

    /**
     * Return the asset-class discriminator for this trade.
     *
     * @return the asset class that identifies which concrete trade type this is
     */
    AssetClass assetClass();

    /**
     * Shared ordering rule that sorts trades by recency and then by reference.
     */
    Comparator<TradeType> NATURAL = Comparator
            .comparing(TradeType::tradeDate).reversed()
            .thenComparing(t -> t.tradeRef().value());

    /**
     * Compare this trade to another trade using the shared natural ordering.
     *
     * @param other the other trade to compare against
     * @return a negative, zero, or positive value depending on whether this trade
     *         sorts before, equal to, or after the other trade
     */
    @Override
    default int compareTo(TradeType other) {
        return NATURAL.compare(this, other);
    }

    /**
     * Enumerates the concrete trade families supported by the sealed hierarchy.
     */
    /**
     * Enumerates the concrete trade families supported by the sealed hierarchy.
     */
    enum AssetClass {
        /**
         * Equity cash-share trade.
         */
        EQUITY,
        /**
         * Foreign-exchange trade.
         */
        FX,
        /**
         * Fixed-income bond trade.
         */
        BOND,
        /**
         * Option-style derivative trade.
         */
        DERIVATIVE
    }
}
