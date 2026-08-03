package com.dbtraining.reconx.dto;

public class TradeRequest extends com.dbtraining.reconx.dto.common.TradeRequest {
    public TradeRequest(String tradeRef, Long counterpartyId, Long instrumentId, java.math.BigDecimal quantity,
                       java.math.BigDecimal price, java.time.LocalDate tradeDate) {
        super(tradeRef, counterpartyId, instrumentId, quantity, price, tradeDate);
    }
}
