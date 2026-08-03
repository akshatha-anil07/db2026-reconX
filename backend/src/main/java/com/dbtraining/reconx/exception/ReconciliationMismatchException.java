package com.dbtraining.reconx.exception;

/**
 * ============================================================================
 * TICKET-ADV031 — Reconciliation mismatch
 *
 * WHAT:    Signals that the internal and external representations of a trade
 *          disagree on a reconciliation-critical field.
 * HOW:     The exception stores the mismatch description so the caller can see
 *          which comparison failed.
 * WHY:     Reconciliation failures are business-significant and should not be
 *          collapsed into generic validation errors.
 * ============================================================================
 */
public class ReconciliationMismatchException extends ReconException {
    /**
     * Create an exception with a detailed mismatch description.
     *
     * @param message the mismatch description to preserve in the exception
     */
    public ReconciliationMismatchException(String message) { super(message); }
}
