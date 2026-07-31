package com.dbtraining.reconx.exception;

/**
 * ============================================================================
 * TICKET-ADV031 — Missing trade reference
 *
 * WHAT:    Signals that no trade exists for the requested reference.
 * HOW:     The exception preserves the missing reference in its message so the
 *          caller can diagnose the lookup that failed.
 * WHY:     Consumers can treat the absence of a trade as a first-class domain
 *          outcome and map it to an HTTP 404 response.
 * ============================================================================
 */
public class TradeNotFoundException extends ReconException {
    /**
     * Create an exception for a missing trade reference.
     *
     * @param tradeRef the trade reference that could not be found
     */
    public TradeNotFoundException(String tradeRef) {
        super("Trade not found: " + tradeRef);
    }
}
