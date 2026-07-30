package com.dbtraining.reconx.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * ============================================================================
 * TICKET-ADV053 — TradeRequest DTO (POST body)
 * TICKET-ADV029 — JSR-380 validation annotations live on the DTO, not the entity
 *
 * WHY:    Putting @Pattern/@Positive/@NotNull on the JPA entity couples
 *         persistence to wire format. The DTO is the wire contract; validate
 *         it before mapping.
 * ============================================================================
 */
public record TradeRequest(
        @NotBlank
        @Pattern(regexp = "^[A-Z]{3}-\\d{8}-\\d{4}$",
                 message = "tradeRef must match AAA-YYYYMMDD-NNNN")
        String tradeRef,

        @NotNull
        @Positive
        Long instrumentId,

        @NotNull
        @Positive
        Long counterpartyId,

        @NotBlank
        @Size(max = 32)
        String assetClass,

        @NotBlank
        @Pattern(regexp = "^(BUY|SELL)$", message = "side must be BUY or SELL")
        @Size(max = 4)
        String side,

        @NotNull
        @Positive
        BigDecimal quantity,

        @NotNull
        @PositiveOrZero
        BigDecimal price,

        @NotNull
        LocalDate tradeDate
) {}
