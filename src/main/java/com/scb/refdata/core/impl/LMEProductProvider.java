package com.scb.refdata.core.impl;

import com.scb.refdata.core.ProductEnricher;

public class LMEProductProvider extends AbstractProductProvider<LMEProduct, DefaultProduct> {

    private LMEProductEnricher lmeProductEnricher = new LMEProductEnricher();

    @Override
    public ProductEnricher<LMEProduct, DefaultProduct> getProductEnricher() {
        return lmeProductEnricher;
    }
}
