package com.scb.refdata.core.impl;

import com.scb.refdata.core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

abstract public class AbstractProductMultiplexer implements ProductMultiplexer<DefaultProduct>, Runnable {
    protected final Logger logger = Logger.getLogger(getClass().getSimpleName());

    private final List<ProductTicker> sources = new ArrayList<>();
    private final ConcurrentLinkedQueue<DefaultProduct> queue = new ConcurrentLinkedQueue<>();

    private Thread eventLoop;

    private AtomicBoolean running = new AtomicBoolean(false);

    protected ProductStore productStore;

    public void start(){
        if(running.get()){
            logger.warning("already running");
            return;
        }
        eventLoop = new Thread(this);
        eventLoop.start();
    }

    public void stop(boolean withMercy){
        running.set(false);
        if(!withMercy){
            eventLoop.interrupt();
        }
        eventLoop = null;

    }
    @Override
    public void run() {
        running.set(true);
        while(running.get()){
            DefaultProduct product = queue.poll();
           if(product != null){
               onMessage(product);
           }
        }
        logger.info("EventLoop exiting..");
    }

    @Override
    public Object register(ProductProvider provider) {
        InnerTicker listener = new InnerTicker(queue, provider, logger, productStore);
        if(sources.add(listener)){
            return listener;
        }

        throw new RuntimeException("register product source failed: " + provider);
    }

    @Override
    public void unregister(Object handler) {
        sources.remove(handler);
    }

    @Override
    abstract public void onMessage(DefaultProduct product);

    private static class InnerTicker implements ProductTicker{
        private final ProductStore productStore;
        private final Logger logger;
        private final ConcurrentLinkedQueue queue;
        private final ProductProvider provider;
        private InnerTicker(ConcurrentLinkedQueue queue, ProductProvider provider, Logger logger, ProductStore productStore){
            this.queue = queue;
            this.provider = provider;
            this.logger = logger;
            this.productStore = productStore;
            provider.register(this);
        }
        @Override
        public void onMessage(Product product) {
            logger.info("received product: " + product);
            productStore.save(product);

            if(! queue.offer(provider.getProductEnricher().enrich(product))){
                throw new RuntimeException("product event lost: " + product);
            }
        }
    }
}
