package com.scb.refdata.core.impl;

import com.scb.refdata.core.ProductMapper;
import com.scb.refdata.core.ProductStore;

import java.util.List;

public class LMEProductMapper implements ProductMapper<PrimeProduct, LMEProduct> {
    private ProductStore productStore;

    public LMEProductMapper(ProductStore store){
        this.productStore = store;
    }


    @Override
    public LMEProduct find(PrimeProduct product) {

        List list = productStore.find(p -> p.getClass().equals(LMEProduct.class) && ((LMEProduct)p).getId().equals(product.getExchangeCode()));

        if(list != null && list.size() > 0){
            return (LMEProduct)list.get(0);
        }

        return null;
    }
}
