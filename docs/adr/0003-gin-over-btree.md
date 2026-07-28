# ADR-0003 — Use GIN index jsonb_path_ops over btree for JSONB metadata

- Status: Accepted
- Date: 2026-06-02
- Deciders: ReconX team

## Context

With `instruments.metadata` using JSONB, querying by nested attributes (e.g. `WHERE metadata @> '{"sector":"Technology"}'`) results in sequential scans across the `instruments` table. Standard B-tree indexes cannot index arbitrary JSON keys/values efficiently for containment operations.

## Decision

Create a Generalized Inverted Index (GIN) using the `jsonb_path_ops` operator class on `instruments.metadata`.

## Consequences

**Positive**
- Dramatic speedup for containment (`@>`) queries, turning sequential scans into bitmap index scans.
- `jsonb_path_ops` produces significantly smaller index sizes and offers better performance than default `jsonb_ops` for containment checks.

**Negative**
- Does not index JSON keys for existence checks (`?` operator); queries must use containment syntax (`@>`).
- Slower insert/update overhead on `instruments` due to GIN index maintenance.
