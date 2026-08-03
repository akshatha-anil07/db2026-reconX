package com.dbtraining.reconx.model;

import java.math.BigDecimal;

/**
 * ============================================================================
 * TICKET-ADV026 — ReconciliationRule enum with configurable thresholds
 *
 * WHAT:    Each enum value carries its own price tolerance (%) and quantity
 *          tolerance (absolute units). {@link #matches} returns true if the
 *          internal versus external trade pair is within tolerance.
 * HOW:     The enum uses stateful constants plus a behaviour method so the
 *          reconciliation engine can ask the same rule object whether a pair
 *          should match.
 * WHY:     Keeping the rule on the enum makes the tolerance policy discoverable
 *          next to the rule name and avoids scattering threshold values across
 *          the engine logic.
 * OBSERVE: PRICE_TOLERANCE_1PCT.matches(p, p*1.005) is true; *1.02 is false.
 * ============================================================================
 */
public enum ReconciliationRule {

    /**
     * Require an exact price and quantity match.
     */
    EXACT(BigDecimal.ZERO, BigDecimal.ZERO),
    /**
     * Allow a one-percent price tolerance while keeping quantity exact.
     */
    PRICE_TOLERANCE_1PCT(new BigDecimal("0.01"), BigDecimal.ZERO),
    /**
     * Allow a fifty-basis-point price tolerance while keeping quantity exact.
     */
    PRICE_TOLERANCE_50BPS(new BigDecimal("0.005"), BigDecimal.ZERO),
    /**
     * Allow a five-unit absolute quantity tolerance while keeping price exact.
     */
    QTY_TOLERANCE_5UNITS(BigDecimal.ZERO, new BigDecimal("5")),
    /**
     * Allow a loose price and quantity tolerance for noisy feeds.
     */
    LOOSE(new BigDecimal("0.05"), new BigDecimal("10"));

    private final BigDecimal priceTolerancePct;
    private final BigDecimal qtyToleranceAbs;

    ReconciliationRule(BigDecimal priceTolerancePct, BigDecimal qtyToleranceAbs) {
        this.priceTolerancePct = priceTolerancePct;
        this.qtyToleranceAbs   = qtyToleranceAbs;
    }

    /**
     * Return the configured price tolerance percentage for this rule.
     *
     * @return the allowed price deviation as a decimal percentage, such as {@code 0.01} for 1%
     */
    public BigDecimal priceTolerancePct() { return priceTolerancePct; }

    /**
     * Return the configured absolute quantity tolerance for this rule.
     *
     * @return the allowed quantity deviation in absolute units
     */
    public BigDecimal qtyToleranceAbs()   { return qtyToleranceAbs; }

    /**
     * Decide whether two prices and quantities are within this rule's tolerance.
     *
     * @param internalPrice the reference price from the internal trade
     * @param internalQty the reference quantity from the internal trade
     * @param externalPrice the comparison price from the external trade
     * @param externalQty the comparison quantity from the external trade
     * @return {@code true} when both the price and quantity deltas satisfy the rule
     */
    public boolean matches(BigDecimal internalPrice, BigDecimal internalQty,
                           BigDecimal externalPrice, BigDecimal externalQty) {
        BigDecimal priceDiff = internalPrice.subtract(externalPrice).abs();
        BigDecimal priceDiffPct = internalPrice.signum() == 0
                ? BigDecimal.ZERO
                : priceDiff.divide(internalPrice, 6, java.math.RoundingMode.HALF_UP);
        BigDecimal qtyDiff = internalQty.subtract(externalQty).abs();

        boolean priceOk = priceDiffPct.compareTo(priceTolerancePct) <= 0;
        boolean qtyOk   = qtyDiff.compareTo(qtyToleranceAbs) <= 0;
        return priceOk && qtyOk;
    }
}
