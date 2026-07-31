package com.dbtraining.reconx.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;

class TradeToStringTest {

    @Test
    void equityTrade_toString_isPiiSafeAndUsesPlainBigDecimalStrings() {
        EquityTrade trade = EquityTrade.builder()
                .tradeRef(TradeRef.of("ABC-20260603-0001"))
                .instrumentSymbol("SAP.DE")
                .quantity(new BigDecimal("100"))
                .price(new BigDecimal("1E+2"))
                .currency("EUR")
                .side(Side.BUY)
                .tradeDate(LocalDate.of(2026, 6, 3))
                .counterpartyId(42L)
                .build();

        assertThat(trade.toString()).contains("ref=ABC-20260603-0001", "symbol=SAP.DE", "qty=100", "price=100", "side=BUY");
        assertThat(trade.toString()).doesNotContain(String.valueOf(trade.counterpartyId()));
        assertThat(trade.toString()).doesNotContain("E+");
    }

    @Test
    void fxTrade_toString_isPiiSafeAndUsesPlainBigDecimalStrings() {
        FXTrade trade = FXTrade.builder()
                .tradeRef(TradeRef.of("ABC-20260603-0002"))
                .ccy1("EUR")
                .ccy2("USD")
                .notionalCcy1(new BigDecimal("1E+2"))
                .fxRate(new BigDecimal("1.25"))
                .side(Side.SELL)
                .tradeDate(LocalDate.of(2026, 6, 3))
                .counterpartyId(7L)
                .build();

        assertThat(trade.toString()).contains("ref=ABC-20260603-0002", "EUR/USD", "notional=100", "rate=1.25", "side=SELL");
        assertThat(trade.toString()).doesNotContain(String.valueOf(trade.counterpartyId()));
        assertThat(trade.toString()).doesNotContain("E+");
    }

    @Test
    void bondTrade_toString_isPiiSafeAndUsesPlainBigDecimalStrings() {
        BondTrade trade = BondTrade.builder()
                .tradeRef(TradeRef.of("ABC-20260603-0003"))
                .isin("US0000000001")
                .faceValue(new BigDecimal("1E+3"))
                .couponRate(new BigDecimal("0.045"))
                .maturityDate(LocalDate.of(2030, 6, 3))
                .currency("USD")
                .side(Side.BUY)
                .tradeDate(LocalDate.of(2026, 6, 3))
                .counterpartyId(99L)
                .build();

        assertThat(trade.toString()).contains("ref=ABC-20260603-0003", "isin=US0000000001", "face=1000", "coupon=0.045", "maturity=2030-06-03", "side=BUY");
        assertThat(trade.toString()).doesNotContain(String.valueOf(trade.counterpartyId()));
        assertThat(trade.toString()).doesNotContain("E+");
    }

    @Test
    void derivativeTrade_toString_isPiiSafeAndUsesPlainBigDecimalStrings() {
        DerivativeTrade trade = DerivativeTrade.builder()
                .tradeRef(TradeRef.of("ABC-20260603-0004"))
                .underlying("AAPL")
                .strike(new BigDecimal("1E+2"))
                .quantity(new BigDecimal("10"))
                .expiry(LocalDate.of(2026, 6, 30))
                .optionType(DerivativeTrade.OptionType.CALL)
                .currency("USD")
                .side(Side.SELL)
                .tradeDate(LocalDate.of(2026, 6, 3))
                .counterpartyId(120L)
                .build();

        assertThat(trade.toString()).contains("ref=ABC-20260603-0004", "CALL AAPL on 2026-06-03", "strike=100", "qty=10", "expiry=2026-06-30", "side=SELL");
        assertThat(trade.toString()).doesNotContain(String.valueOf(trade.counterpartyId()));
        assertThat(trade.toString()).doesNotContain("E+");
    }
}
