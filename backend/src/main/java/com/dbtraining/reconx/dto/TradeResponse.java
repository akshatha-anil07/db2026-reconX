package com.dbtraining.reconx.dto;

public class TradeResponse extends com.dbtraining.reconx.dto.common.TradeResponse {
    public TradeResponse(Long id, String tradeRef, Long counterpartyId, String counterpartyName, Long instrumentId,
                         String instrumentSymbol, java.math.BigDecimal quantity, java.math.BigDecimal price,
                         java.time.LocalDate tradeDate, String status, java.time.Instant createdAt,
                         java.time.Instant modifiedAt) {
        super(id, tradeRef, counterpartyId, counterpartyName, instrumentId, instrumentSymbol, quantity, price,
                tradeDate, status, createdAt, modifiedAt);
    }
}
