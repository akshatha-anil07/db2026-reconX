package com.dbtraining.reconx.model;

import java.util.Map;

/**
 * ============================================================================
 * TICKET-ADV023 — TradeFactory: build a TradeType by asset-class string
 *
 * WHAT:    Single entry point that takes an asset-class string plus a map of
 *          field values and returns the correct concrete trade implementation.
 * HOW:     The factory parses the asset-class string, dispatches to a typed
 *          helper, and relies on the sealed hierarchy to keep the switch
 *          exhaustive.
 * WHY:     The Kafka consumer and REST POST endpoint both need to convert an
 *          untyped payload into a typed trade. Centralising the construction
 *          logic keeps the parsing rules in one place.
 * OBSERVE: TradeFactoryTest.create_unknownAssetClass_throws fails when a new
 *          trade implementation is added without updating the switch.
 * HINT:    The sealed hierarchy guarantees that every concrete TradeType is
 *          listed in TradeType.permits, so the factory switch can be made
 *          exhaustive over the asset-class enum.
 * ============================================================================
 */
public final class TradeFactory {

    private TradeFactory() { }

    /**
     * Create a trade from a raw asset-class name and a property map.
     *
     * @param assetClass the asset-class identifier to parse, such as {@code equity}
     * @param p the raw field values used to build the trade; the factory expects
     *          the same field names that the concrete trade builders consume
     * @return the concrete trade implementation selected for the given asset class
     * @throws UnsupportedOperationException until the factory implementation is completed
     */
    public static TradeType create(String assetClass, Map<String, Object> p) {
        throw new UnsupportedOperationException("TICKET-ADV023");
    }
}
