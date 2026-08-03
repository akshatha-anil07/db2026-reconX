package com.dbtraining.reconx.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * ============================================================================
 * TICKET-ADV031 — RFC 7807 problem mapping for domain failures
 *
 * WHAT:    Central handler for domain and validation exceptions thrown by the
 *          API layer.
 * HOW:     Each handler method translates a domain exception into a
 *          structured {@link ProblemDetail} with an HTTP status.
 * WHY:     Clients receive consistent, machine-readable error payloads instead
 *          of relying on ad-hoc exception messages or stack traces.
 * ============================================================================
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Create the global exception handler.
     */
    public GlobalExceptionHandler() {
    }

    /**
     * Map a missing-trade failure to an HTTP 404 response.
     *
     * @param ex the missing-trade exception to translate
     * @return a {@link ProblemDetail} describing the failure with status 404
     * @throws UnsupportedOperationException until the handler implementation is completed
     */
    @ExceptionHandler(TradeNotFoundException.class)
    public ProblemDetail notFound(TradeNotFoundException ex) {
        // TODO(TICKET-ADV062): return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        throw new UnsupportedOperationException("TICKET-ADV062");
    }

    /**
     * Map a duplicate-reference failure to an HTTP 409 response.
     *
     * @param ex the duplicate-reference exception to translate
     * @return a {@link ProblemDetail} describing the failure with status 409
     * @throws UnsupportedOperationException until the handler implementation is completed
     */
    @ExceptionHandler(DuplicateTradeRefException.class)
    public ProblemDetail duplicate(DuplicateTradeRefException ex) {
        // TODO(TICKET-ADV062): map DuplicateTradeRefException -> HttpStatus.CONFLICT (409).
        throw new UnsupportedOperationException("TICKET-ADV062");
    }

    /**
     * Map a business-validation failure to an HTTP 400 response.
     *
     * @param ex the invalid-trade exception to translate
     * @return a {@link ProblemDetail} describing the failure with status 400
     * @throws UnsupportedOperationException until the handler implementation is completed
     */
    @ExceptionHandler(InvalidTradeException.class)
    public ProblemDetail invalid(InvalidTradeException ex) {
        // TODO(TICKET-ADV062): map InvalidTradeException -> HttpStatus.BAD_REQUEST (400).
        throw new UnsupportedOperationException("TICKET-ADV062");
    }

    /**
     * Map a reconciliation mismatch to an HTTP 422 response.
     *
     * @param ex the reconciliation-mismatch exception to translate
     * @return a {@link ProblemDetail} describing the failure with status 422
     * @throws UnsupportedOperationException until the handler implementation is completed
     */
    @ExceptionHandler(ReconciliationMismatchException.class)
    public ProblemDetail mismatch(ReconciliationMismatchException ex) {
        // TODO(TICKET-ADV062): map ReconciliationMismatchException -> HttpStatus.UNPROCESSABLE_ENTITY (422).
        throw new UnsupportedOperationException("TICKET-ADV062");
    }

    /**
     * Map DTO validation failures to an HTTP 400 response.
     *
     * @param ex the Spring validation exception containing field-level errors
     * @return a {@link ProblemDetail} describing the validation failure with status 400
     * @throws UnsupportedOperationException until the handler implementation is completed
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail validation(MethodArgumentNotValidException ex) {
        // TODO(TICKET-ADV062): join field errors ("field: message; ...") and return BAD_REQUEST ProblemDetail.
        //   Hint: ex.getBindingResult().getFieldErrors().stream().map(...).collect(Collectors.joining("; "))
        throw new UnsupportedOperationException("TICKET-ADV062");
    }

    /**
     * Map bean-validation violations to an HTTP 400 response.
     *
     * @param ex the constraint-violation exception to translate
     * @return a {@link ProblemDetail} describing the validation failure with status 400
     * @throws UnsupportedOperationException until the handler implementation is completed
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail constraint(ConstraintViolationException ex) {
        // TODO(TICKET-ADV062): map ConstraintViolationException -> HttpStatus.BAD_REQUEST (400).
        throw new UnsupportedOperationException("TICKET-ADV062");
    }
}
