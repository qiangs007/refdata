package com.scb.refdata.core.impl;

import com.scb.refdata.core.ProductEnricher;
import com.scb.refdata.core.ProductMapper;

public class PrimeProductEnricher implements ProductEnricher<PrimeProduct, DefaultProduct> {

    private ProductMapper<PrimeProduct, LMEProduct> productMapper;

    private LMEProductEnricher lmeProductEnricher;

    public PrimeProductEnricher(ProductMapper<PrimeProduct, LMEProduct> productMapper, LMEProductEnricher lmeProductEnricher){
        this.productMapper = productMapper;
        this.lmeProductEnricher = lmeProductEnricher;
    }

    @Override
    public DefaultProduct enrich(PrimeProduct product) {

        LMEProduct lmeProduct = productMapper.find(product);
        if(lmeProduct == null){
            return null;
        }

        DefaultProduct out = lmeProductEnricher.enrich(lmeProduct);
        out.setTradable(false);

        return out;
    }
}
