package com.dbtraining.reconx.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TradeEntityTest {

    @Test
    void tradeEntityUsesIdBasedEqualityAndDefaultsToPending() {
        Counterparty counterparty = new Counterparty("ACME");
        Instrument instrument = new Instrument("AAPL");

        Trade trade = new Trade("T-100", counterparty, instrument, BigDecimal.TEN, BigDecimal.ONE, LocalDate.now());

        assertThat(trade.getStatus()).isEqualTo(TradeStatus.PENDING);
        assertThat(trade).isNotEqualTo(new Trade());
    }
}
