package com.dbtraining.reconx.domain;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class InstrumentJsonbTest {

    @Test
    void metadataRoundTripsAsMap() {
        Instrument instrument = new Instrument("AAPL", "Apple Inc.", AssetClass.EQUITY, "USD");
        Map<String, Object> metadata = new LinkedHashMap<>();
        metadata.put("isin", "GB00B16GWD56");
        metadata.put("cusip", "037833100");
        instrument.setMetadata(metadata);

        Instrument reloaded = new Instrument("AAPL", "Apple Inc.", AssetClass.EQUITY, "USD");
        reloaded.setMetadata(new LinkedHashMap<>(instrument.getMetadata()));

        assertThat(reloaded.getMetadata()).containsEntry("isin", "GB00B16GWD56");
        assertThat(reloaded.getMetadata()).containsEntry("cusip", "037833100");
    }
}
