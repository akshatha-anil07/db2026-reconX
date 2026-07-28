# Architecture Decision Records (ADRs)

This directory contains Architecture Decision Records (ADRs) for the ReconX system in Michael Nygard format.

## Prompt Template for AI-Assisted ADR Generation

```text
You are an enterprise software architect. Write an Architecture Decision Record
(ADR) in the Michael Nygard format (Title, Status, Context, Decision,
Consequences) for the following decision.

System: ReconX, a near-prod trade reconciliation platform.
Stack: PostgreSQL 16, Spring Boot 3, Kafka, React.
Scale: ~50,000 trades/day, 5-year retention, 10 concurrent recon analysts.

Decision to record: <ONE LINE DESCRIBING THE DECISION>

Alternatives we considered: <LIST 2-3>

Constraints / forces: <LIST 2-3>

Format: Markdown, Nygard 5-section template, no fluff. Keep under 300 words.
Include a "Status: Accepted | Date: <YYYY-MM-DD>" line.
```

## Index of ADRs

- [0001 — Partition the trades table by trade_date](0001-partition-trades-by-date.md)
- [0002 — Use JSONB for flexible instrument metadata](0002-jsonb-metadata.md)
- [0003 — Use GIN index jsonb_path_ops over btree for JSONB metadata](0003-gin-over-btree.md)
