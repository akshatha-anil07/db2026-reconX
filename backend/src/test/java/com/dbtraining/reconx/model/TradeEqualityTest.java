package com.dbtraining.reconx.model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TradeEqualityTest {

    @Test
    void tradesWithSameTradeRef_areEqualAndCollapseInHashSet() {
        EquityTrade first = instantiateEquity("EQU-20260603-0001", "SAP.DE", new BigDecimal("100"), new BigDecimal("100"), Side.BUY, 1L);
        EquityTrade duplicate = instantiateEquity("EQU-20260603-0001", "IBM.N", new BigDecimal("200"), new BigDecimal("90"), Side.SELL, 2L);

        HashSet<TradeType> set = new HashSet<>(List.of(first, duplicate));

        assertThat(set).hasSize(1);
        assertThat(first).isEqualTo(duplicate);
        assertThat(first.hashCode()).isEqualTo(duplicate.hashCode());
    }

    private static EquityTrade instantiateEquity(String ref, String symbol, BigDecimal quantity, BigDecimal price, Side side, long counterpartyId) {
        try {
            Constructor<EquityTrade> constructor = EquityTrade.class.getDeclaredConstructor(EquityTrade.Builder.class);
            constructor.setAccessible(true);
            EquityTrade.Builder builder = EquityTrade.builder()
                    .tradeRef(TradeRef.of(ref))
                    .instrumentSymbol(symbol)
                    .quantity(quantity)
                    .price(price)
                    .currency("EUR")
                    .side(side)
                    .tradeDate(LocalDate.of(2026, 6, 3))
                    .counterpartyId(counterpartyId);
            return constructor.newInstance(builder);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("Unable to instantiate EquityTrade", e);
        }
    }
}
