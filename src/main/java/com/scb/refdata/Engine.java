package com.scb.refdata;

import com.scb.refdata.core.ProductProvider;
import com.scb.refdata.core.ProductStore;
import com.scb.refdata.core.impl.*;

public class Engine extends AbstractProductMultiplexer {

    public Engine(){
        productStore = new DefaultProductStore();
        register(new LMEProductProvider());
        register(new PrimeProductProvider(productStore));
    }

    public Engine(ProductStore productStore, ProductProvider... providers){
        this.productStore = productStore;

        for(ProductProvider provider: providers){
            this.register(provider);
        }
    }
    @Override
    public void onMessage(DefaultProduct product) {
        publish(product);

    }

    public void publish(DefaultProduct product){
        logger.info("publishing product: " + product);
    }
}
