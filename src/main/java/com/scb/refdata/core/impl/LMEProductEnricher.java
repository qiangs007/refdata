package com.scb.refdata.core.impl;

import com.scb.refdata.core.ProductEnricher;

public class LMEProductEnricher implements ProductEnricher<LMEProduct, DefaultProduct> {

    @Override
    public DefaultProduct enrich(LMEProduct product) {

        DefaultProduct out = new DefaultProduct();
        out.setDeliveryDate(product.getDeliveryDate());
        out.setId(product.getId());
        out.setLastTradingDate(product.getLastTradingDate());
        out.setLabel(product.getLabel());
        out.setMarket(formatMarket(product.getMarket()));
        out.setTradable(true);

        return out;
    }

    private static String formatMarket(String lmeMarket){
        if(lmeMarket != null){
            String[] arr = lmeMarket.split("_");
            if(arr.length > 1){
                return arr[arr.length -1];
            }
        }
        return "";
    }
}
