# TICKET-ADV016 — Jira / Kanban Setup & Epics Breakdown

## Overview

This document defines the Jira / GitHub Projects Kanban board configuration, epics breakdown, issue card template, and acceptance criteria mapping for Day 1 deliverables.

---

## Epics Structure

### Epic 1: RECONX-E1 — Day 1: Architecture & Setup
- **TICKET-ADV001**: Create GitHub repo with branch protection
- **TICKET-ADV002**: Design C4 Context diagram
- **TICKET-ADV003**: Design C4 Container diagram
- **TICKET-ADV004**: Design C4 Component diagram

### Epic 2: RECONX-E2 — Day 1: Schema & Analytics
- **TICKET-ADV006**: Design ER model (8 entities)
- **TICKET-ADV007**: CREATE TABLE with monthly partitioning
- **TICKET-ADV008**: Materialised view: mv_daily_recon_summary
- **TICKET-ADV009**: Add JSONB column to instruments
- **TICKET-ADV010**: Window Function: VWAP per instrument per day
- **TICKET-ADV011**: Recursive CTE: trade lifecycle rollup

### Epic 3: RECONX-E3 — Day 1: Liquibase & Tooling
- **TICKET-ADV012**: Liquibase master changelog
- **TICKET-ADV013**: Add rollback tags
- **TICKET-ADV014**: Add preconditions
- **TICKET-ADV015**: Use Claude to generate ADRs
- **TICKET-ADV016**: Set up Jira / Kanban with epics
- **TICKET-ADV017**: Seed data: 10 counterparties, 50 instruments, 500 trades

---

## Column Workflow

```text
[ Backlog ] ──► [ To Do ] ──► [ In Progress ] ──► [ In Review (PR Open) ] ──► [ Done ]
```

---

## Board Field Schema

| Field Name | Type | Options / Format | Description |
|---|---|---|---|
| **Exercise ID** | Text | `TICKET-ADV001` .. `TICKET-ADV017` | Codebase cross-reference |
| **Estimate** | Single Select | `1`, `2`, `3`, `5`, `8` | Story Points |
| **Owner** | Assignee | Team member handle | Assigned developer |
| **Linked PR** | Text / Link | PR URL | Github PR link |
| **Status** | Single Select | `Backlog`, `To Do`, `In Progress`, `In Review`, `Done` | Workflow state |

---

## Card Inventory & Acceptance Criteria Mapping

| Ticket ID | Title | Epic | Story Points | Status |
|---|---|---|---|---|
| **TICKET-ADV001** | Create GitHub repo with branch protection | RECONX-E1 | 2 | Done |
| **TICKET-ADV002** | Design C4 Context diagram | RECONX-E1 | 3 | Done |
| **TICKET-ADV003** | Design C4 Container diagram | RECONX-E1 | 3 | Done |
| **TICKET-ADV004** | Design C4 Component diagram | RECONX-E1 | 3 | Done |
| **TICKET-ADV006** | Design ER model (8 entities) | RECONX-E2 | 3 | Done |
| **TICKET-ADV007** | CREATE TABLE with monthly partitioning | RECONX-E2 | 5 | Done |
| **TICKET-ADV008** | Materialised view: mv_daily_recon_summary | RECONX-E2 | 3 | Done |
| **TICKET-ADV009** | Add JSONB column to instruments | RECONX-E2 | 3 | Done |
| **TICKET-ADV010** | Window Function: VWAP per instrument per day | RECONX-E2 | 3 | Done |
| **TICKET-ADV011** | Recursive CTE: trade lifecycle rollup | RECONX-E2 | 3 | Done |
| **TICKET-ADV012** | Liquibase master changelog | RECONX-E3 | 3 | Done |
| **TICKET-ADV013** | Add rollback tags | RECONX-E3 | 2 | Done |
| **TICKET-ADV014** | Add preconditions | RECONX-E3 | 2 | Done |
| **TICKET-ADV015** | Use Claude to generate ADRs | RECONX-E3 | 2 | Done |
| **TICKET-ADV016** | Set up Jira / Kanban with epics | RECONX-E3 | 1 | Done |
| **TICKET-ADV017** | Seed data (10 / 50 / 500) | RECONX-E3 | 3 | Done |
