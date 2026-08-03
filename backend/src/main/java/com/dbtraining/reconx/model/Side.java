package com.dbtraining.reconx.model;

/**
 * ============================================================================
 * TICKET-ADV031 — Public contract for the trade-side enum
 *
 * WHAT:    Enumerates the two business sides that can appear on a trade.
 * HOW:     The enum provides a typed alternative to a free-form string so the
 *          compiler can reject misspelled or unsupported values.
 * WHY:     The reconciliation and reporting layers depend on the side being a
 *          known, finite value rather than an arbitrary string that could drift
 *          out of sync with the domain vocabulary.
 * ============================================================================
 */
public enum Side {
    /**
     * The trade represents a purchase or acquisition.
     */
    BUY,
    /**
     * The trade represents a sale or disposal.
     */
    SELL
}
