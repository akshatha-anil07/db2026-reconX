package com.dbtraining.reconx.model;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * ============================================================================
 * TICKET-ADV024 — Immutable value object: TradeRef (natural key for a trade)
 *
 * WHAT:    Strongly-typed wrapper around the trade reference string. Format:
 *          AAA-YYYYMMDD-NNNN  (3 letters, 8-digit date, 4 digits).
 * HOW:     The compact constructor validates the value against a regular
 *          expression and rejects null or malformed inputs immediately.
 * WHY:     A bare String "trade reference" can be confused with any other
 *          String — counterparty name, instrument symbol. TradeRef as a
 *          distinct type makes those mix-ups a compile error.
 * OBSERVE: TradeRef.of("EQU-20260602-0001") works; .of("foo") throws.
 * ============================================================================
 *
 * @param value the canonical trade reference in the format {@code AAA-YYYYMMDD-NNNN}
 */
public record TradeRef(String value) {

    private static final Pattern PATTERN = Pattern.compile("^[A-Z]{3}-\\d{8}-\\d{4}$");

    /**
     * Create a validated trade reference.
     *
     * @param value the raw reference string to validate
     * @throws NullPointerException if {@code value} is {@code null}
     * @throws IllegalArgumentException if {@code value} does not match the expected
     *         pattern {@code AAA-YYYYMMDD-NNNN}
     */
    public TradeRef {
        Objects.requireNonNull(value, "tradeRef value");
        if (!PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException(
                    "Invalid tradeRef format '%s' — expected AAA-YYYYMMDD-NNNN".formatted(value));
        }
    }

    /**
     * Create a trade reference from a raw string value.
     *
     * @param value the string to wrap as a {@link TradeRef}
     * @return a validated trade reference instance
     * @throws NullPointerException if {@code value} is {@code null}
     * @throws IllegalArgumentException if {@code value} does not match the expected format
     */
    public static TradeRef of(String value) {
        return new TradeRef(value);
    }

    /**
     * Return the underlying reference string.
     *
     * @return the canonical reference string representation
     */
    @Override
    public String toString() {
        return value;
    }
}
