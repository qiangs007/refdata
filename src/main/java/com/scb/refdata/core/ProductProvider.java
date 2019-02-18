package com.scb.refdata.core;

public interface ProductProvider<IN extends Product, OUT extends Product>{
    void publish(IN product);

    Object register(ProductTicker<IN> ticker);
    void unregister(Object handler);

    ProductEnricher<IN, OUT> getProductEnricher();
}
