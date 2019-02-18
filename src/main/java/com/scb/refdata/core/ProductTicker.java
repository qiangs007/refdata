package com.scb.refdata.core;

public interface ProductTicker<T extends Product> {
    void onMessage(T product);
}
