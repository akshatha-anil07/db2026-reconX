package com.dbtraining.reconx.exception;

/**
 * ============================================================================
 * TICKET-ADV031 — Duplicate trade reference
 *
 * WHAT:    Signals that a trade reference already exists in the store.
 * HOW:     The exception preserves the conflicting reference so the caller can
 *          decide whether to retry with a new key or report a conflict.
 * WHY:     Duplicate references are a domain-level integrity violation and are
 *          best surfaced as a distinct error rather than silently overwritten.
 * ============================================================================
 */
public class DuplicateTradeRefException extends ReconException {
    /**
     * Create an exception for a duplicate trade reference.
     *
     * @param tradeRef the reference that already exists in the store
     */
    public DuplicateTradeRefException(String tradeRef) {
        super("Duplicate tradeRef: " + tradeRef);
    }
}
