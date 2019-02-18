package com.scb.refdata.core;

import java.util.List;
import java.util.function.Predicate;

public interface ProductStore<T extends Product> {
    void clear();
    void remove(Product product);

    void save(Product product);

    List<Product> find(Predicate<T> predicate);
}
