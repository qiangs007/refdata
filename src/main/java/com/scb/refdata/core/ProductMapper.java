package com.scb.refdata.core;

public interface ProductMapper<IN extends Product, OUT extends Product> {
    OUT find(IN product);
}
