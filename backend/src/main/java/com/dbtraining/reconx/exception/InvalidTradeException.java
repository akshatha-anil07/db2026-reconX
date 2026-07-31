package com.dbtraining.reconx.exception;

/**
 * ============================================================================
 * TICKET-ADV031 — Invalid trade payload
 *
 * WHAT:    Signals that a trade request failed domain validation.
 * HOW:     The exception carries the validation message supplied by the
 *          caller or service layer so the REST layer can surface it directly.
 * WHY:     A single exception type makes invalid-input failures easy to map to
 *          HTTP 400 responses without custom branching in every caller.
 * ============================================================================
 */
public class InvalidTradeException extends ReconException {
    /**
     * Create an exception for an invalid trade payload.
     *
     * @param message the validation failure message to expose to the caller
     */
    public InvalidTradeException(String message) { super(message); }
}
