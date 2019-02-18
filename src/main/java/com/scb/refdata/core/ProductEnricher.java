package com.scb.refdata.core;

public interface ProductEnricher<IN extends Product, OUT extends Product> {
    OUT enrich(IN product);
}
