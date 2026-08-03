package com.dbtraining.reconx.exception;

/**
 * ============================================================================
 * TICKET-ADV031 — Root contract for domain exceptions
 *
 * WHAT:    Abstract parent for every domain-level exception raised by the
 *          reconciliation service.
 * HOW:     The class extends {@link RuntimeException} so controller and
 *          service layers can throw business failures without checked-exception
 *          boilerplate, while subclasses remain grouped under one umbrella type.
 * WHY:     A single root type allows the REST layer to catch every domain
 *          exception with one handler and map it consistently to an RFC-7807
 *          ProblemDetail response.
 * ============================================================================
 */
public abstract class ReconException extends RuntimeException {
    /**
     * Create a domain exception with a message.
     *
     * @param message the human-readable explanation of the failure
     */
    protected ReconException(String message) { super(message); }

    /**
     * Create a domain exception with a message and a cause.
     *
     * @param message the human-readable explanation of the failure
     * @param cause the underlying cause that triggered the exception
     */
    protected ReconException(String message, Throwable cause) { super(message, cause); }
}
