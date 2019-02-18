package com.scb.refdata.core.impl;

import com.scb.refdata.core.Product;
import com.scb.refdata.core.ProductStore;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DefaultProductStore implements ProductStore<Product> {

    Map<String, Product> productCache = Collections.synchronizedMap(new HashMap<>());

    @Override
    public void save(Product product) {
        productCache.put(getKey(product), product);
    }

    @Override
    public List<Product> find(Predicate<Product> predicate) {
        return productCache.values().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        productCache.clear();
    }

    @Override
    public void remove(Product product) {
        productCache.remove(getKey(product));
    }

    private String getKey(Product product){
        if(product != null && product.getId() != null){
            return product.getClass().getSimpleName() + "_" + product.getId();
        }

        return null;
    }
}
