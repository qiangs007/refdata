package com.scb.refdata.core.impl;

import com.scb.refdata.core.ProductEnricher;
import com.scb.refdata.core.ProductStore;

public class PrimeProductProvider extends AbstractProductProvider<PrimeProduct, DefaultProduct> {

    private PrimeProductEnricher primeProductEnricher;


    public PrimeProductProvider(ProductStore productStore){
        primeProductEnricher =  new PrimeProductEnricher(new LMEProductMapper(productStore), new LMEProductEnricher());
    }

    @Override
    public ProductEnricher<PrimeProduct, DefaultProduct> getProductEnricher() {
        return primeProductEnricher;
    }
}
