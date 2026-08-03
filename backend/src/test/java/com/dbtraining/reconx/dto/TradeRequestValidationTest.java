package com.dbtraining.reconx.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class TradeRequestValidationTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void invalidTradeRequestFailsValidation() {
        var request = new TradeRequest(
                "bad-ref",
                0L,
                0L,
                " ",
                "HOLD",
                BigDecimal.ZERO,
                BigDecimal.valueOf(-1),
                null
        );

        Set<ConstraintViolation<TradeRequest>> violations = validator.validate(request);

        assertThat(violations).extracting(ConstraintViolation::getMessage)
                .contains(
                        "tradeRef must match AAA-YYYYMMDD-NNNN",
                        "must be greater than 0",
                        "must be greater than 0",
                        "must not be blank",
                        "side must be BUY or SELL",
                        "must be greater than 0",
                        "must be greater than or equal to 0",
                        "must not be null"
                );
    }

    @Test
    void validTradeRequestPassesValidation() {
        var request = new TradeRequest(
                "ABC-20250101-0001",
                1L,
                2L,
                "EQUITY",
                "BUY",
                BigDecimal.TEN,
                BigDecimal.valueOf(100.50),
                LocalDate.of(2025, 1, 2)
        );

        Set<ConstraintViolation<TradeRequest>> violations = validator.validate(request);

        assertThat(violations).isEmpty();
    }
}
