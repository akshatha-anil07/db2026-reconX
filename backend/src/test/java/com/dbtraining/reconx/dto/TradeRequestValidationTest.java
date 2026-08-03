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
                " ",
                null,
                null,
                BigDecimal.ZERO,
                BigDecimal.valueOf(-1),
                LocalDate.now().plusDays(1)
        );

        Set<ConstraintViolation<TradeRequest>> violations = validator.validate(request);

        assertThat(violations).extracting(ConstraintViolation::getMessage)
                .contains(
                        "must not be blank",
                        "must not be null",
                        "must not be null",
                        "must be greater than 0.0",
                        "must be greater than 0.0",
                        "must be a date in the past or in the present"
                );
    }

    @Test
    void validTradeRequestPassesValidation() {
        var request = new TradeRequest(
                "ABC-1001",
                1L,
                2L,
                BigDecimal.TEN,
                BigDecimal.valueOf(100.50),
                LocalDate.of(2025, 1, 2)
        );

        Set<ConstraintViolation<TradeRequest>> violations = validator.validate(request);

        assertThat(violations).isEmpty();
    }
}
