package com.dbtraining.reconx.model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

class TradeTypeOrderingTest {

    @Test
    void naturalOrdering_sortsNewestFirst_andBreaksTiesByTradeRef() {
        TradeType newest = equityTrade("EQU-20260603-0001", LocalDate.of(2026, 6, 3));
        TradeType sameDateEarlier = fxTrade("FXT-20260603-0002", LocalDate.of(2026, 6, 3));
        TradeType older = bondTrade("BND-20260602-0001", LocalDate.of(2026, 6, 2));
        TradeType latest = derivativeTrade("DER-20260604-0001", LocalDate.of(2026, 6, 4));

        TreeSet<TradeType> sorted = new TreeSet<>(List.of(newest, sameDateEarlier, older, latest));

        assertThat(sorted)
                .extracting(t -> t.tradeRef().value())
                .containsExactly("DER-20260604-0001", "EQU-20260603-0001", "FXT-20260603-0002", "BND-20260602-0001");

        assertThat(newest.compareTo(sameDateEarlier)).isLessThan(0);
        assertThat(sameDateEarlier.compareTo(newest)).isGreaterThan(0);
    }

    private static EquityTrade equityTrade(String ref, LocalDate tradeDate) {
        EquityTrade.Builder builder = new EquityTrade.Builder();
        builder.tradeRef(TradeRef.of(ref))
                .instrumentSymbol("SAP.DE")
                .quantity(new BigDecimal("100"))
                .price(new BigDecimal("100"))
                .currency("EUR")
                .side(Side.BUY)
                .tradeDate(tradeDate)
                .counterpartyId(1L);
        return instantiate(EquityTrade.class, builder);
    }

    private static FXTrade fxTrade(String ref, LocalDate tradeDate) {
        FXTrade.Builder builder = new FXTrade.Builder();
        builder.tradeRef(TradeRef.of(ref))
                .ccy1("EUR")
                .ccy2("USD")
                .notionalCcy1(new BigDecimal("1000"))
                .fxRate(new BigDecimal("1.10"))
                .side(Side.BUY)
                .tradeDate(tradeDate)
                .counterpartyId(2L);
        return instantiate(FXTrade.class, builder);
    }

    private static BondTrade bondTrade(String ref, LocalDate tradeDate) {
        BondTrade.Builder builder = new BondTrade.Builder();
        builder.tradeRef(TradeRef.of(ref))
                .isin("US0000000001")
                .faceValue(new BigDecimal("1000"))
                .couponRate(new BigDecimal("0.05"))
                .maturityDate(tradeDate.plusYears(1))
                .currency("USD")
                .side(Side.BUY)
                .tradeDate(tradeDate)
                .counterpartyId(3L);
        return instantiate(BondTrade.class, builder);
    }

    private static DerivativeTrade derivativeTrade(String ref, LocalDate tradeDate) {
        DerivativeTrade.Builder builder = new DerivativeTrade.Builder();
        builder.tradeRef(TradeRef.of(ref))
                .underlying("AAPL")
                .strike(new BigDecimal("10"))
                .quantity(new BigDecimal("100"))
                .expiry(tradeDate.plusMonths(1))
                .optionType(DerivativeTrade.OptionType.CALL)
                .currency("USD")
                .side(Side.BUY)
                .tradeDate(tradeDate)
                .counterpartyId(4L);
        return instantiate(DerivativeTrade.class, builder);
    }

    private static <T> T instantiate(Class<T> type, Object builder) {
        try {
            Constructor<T> constructor = type.getDeclaredConstructor(builder.getClass());
            constructor.setAccessible(true);
            return constructor.newInstance(builder);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("Unable to instantiate " + type.getSimpleName(), e);
        }
    }
}
