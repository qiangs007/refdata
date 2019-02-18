package com.scb.refdata.core.impl;

import com.scb.refdata.core.Product;
import com.scb.refdata.core.ProductProvider;
import com.scb.refdata.core.ProductTicker;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractProductProvider<IN extends Product, OUT extends Product> implements ProductProvider<IN, OUT> {
    private List<ProductTicker<IN>> listeners = new ArrayList<>();

    @Override
    public void publish(IN product) {
        for(ProductTicker<IN> listener: listeners){
            listener.onMessage(product);
        }
    }

    @Override
    public Object register(ProductTicker<IN> ticker) {
        if(listeners.add(ticker)){
            return ticker;
        }
        return null;
    }

    @Override
    public void unregister(Object handler) {
        listeners.remove(handler);
    }

}
