package com.dbtraining.reconx.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Instrument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    protected Instrument() {}

    public Instrument(String symbol) {
        this.symbol = symbol;
    }

    public Long getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }
}
