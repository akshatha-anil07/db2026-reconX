# ADR-0002 — Use JSONB for flexible instrument metadata

- Status: Accepted
- Date: 2026-06-02
- Deciders: ReconX team

## Context

Financial instruments (Equities, Fixed Income, FX, Commodities, Derivatives) have disparate attribute requirements (e.g. coupon rate for bonds, expiry for futures, sector for stocks). Adding dedicated relational columns for every asset class attribute creates sparse, bloated tables with hundreds of nullable columns and constant DDL migrations as new asset types are introduced.

## Decision

Store asset-class-specific attributes in a `metadata` JSONB column on the `instruments` table with a default empty JSON object (`'{}'::jsonb`). Standard common fields (symbol, name, currency, ISIN, asset_class) remain relational columns.

## Consequences

**Positive**
- Eliminates schema migrations when new instrument metadata fields are added.
- Accommodates disparate asset class structures without sparse columns.
- Preserves full JSON querying capability directly in PostgreSQL.

**Negative**
- Schema enforcement moves from database-level constraints to application layer validation.
- Slight storage overhead compared to raw scalar types.
