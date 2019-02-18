package com.scb.refdata.core;

public interface ProductMultiplexer<T extends Product> extends ProductTicker<T>{
    Object register(ProductProvider ticker);
    void unregister(Object handler);
}
