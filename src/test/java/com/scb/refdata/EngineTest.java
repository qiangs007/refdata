package com.scb.refdata;

import com.scb.refdata.core.impl.*;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class EngineTest {



    @Test
    public void testStory1(){

        DefaultProductStore productStore = new DefaultProductStore();
        LMEProductProvider lmeProductProvider = new LMEProductProvider();
        PrimeProductProvider primeProductProvider = new PrimeProductProvider(productStore);


        Engine engine = spy(new Engine(productStore, lmeProductProvider, primeProductProvider));

        engine.start();

        LMEProduct lmeProduct = new LMEProduct();

        lmeProduct.setId("PB_03_2018");
        lmeProduct.setLastTradingDate(LocalDate.of(2018, 3, 15));
        lmeProduct.setDeliveryDate(LocalDate.of(2018, 3, 17));
        lmeProduct.setMarket("LME_PB");
        lmeProduct.setLabel("Lead 13 March 2018");

        lmeProductProvider.publish(lmeProduct);

        ArgumentCaptor<DefaultProduct> argument = ArgumentCaptor.forClass(DefaultProduct.class);

        verify(engine).publish(argument.capture());

        assertEquals(lmeProduct.getId(), argument.getValue().getId());
        assertEquals(true, argument.getValue().isTradable());
        assertEquals("PB", argument.getValue().getMarket());

        engine.stop(true);
    }

    @Test
    public void testStory2(){

        DefaultProductStore productStore = new DefaultProductStore();
        LMEProductProvider lmeProductProvider = new LMEProductProvider();
        PrimeProductProvider primeProductProvider = new PrimeProductProvider(productStore);


        Engine engine = spy(new Engine(productStore, lmeProductProvider, primeProductProvider));

        engine.start();

        LMEProduct lmeProduct = new LMEProduct();

        lmeProduct.setId("PB_03_2018");
        lmeProduct.setLastTradingDate(LocalDate.of(2018, 3, 15));
        lmeProduct.setDeliveryDate(LocalDate.of(2018, 3, 17));
        lmeProduct.setMarket("LME_PB");
        lmeProduct.setLabel("Lead 13 March 2018");

        lmeProductProvider.publish(lmeProduct);

        ArgumentCaptor<DefaultProduct> argument = ArgumentCaptor.forClass(DefaultProduct.class);

        verify(engine).publish(argument.capture());

        assertEquals(lmeProduct.getId(), argument.getValue().getId());
        assertEquals(true, argument.getValue().isTradable());
        assertEquals("PB", argument.getValue().getMarket());


        PrimeProduct primeProduct = new PrimeProduct();
        primeProduct.setId("PB_03_2018");
        primeProduct.setLastTradingDate(LocalDate.of(2018, 3, 14));
        primeProduct.setDeliveryDate(LocalDate.of(2018, 3, 18));
        primeProduct.setMarket("LME_PB");
        primeProduct.setLabel("Lead 13 March 2018");
        primeProduct.setExchangeCode("PB_03_2018");
        primeProduct.setTradable(false);

        primeProductProvider.publish(primeProduct);
        verify(engine, times(2)).publish(argument.capture());

        assertEquals("PB_03_2018", argument.getValue().getId());
        assertEquals(false, argument.getValue().isTradable());
        assertEquals("PB", argument.getValue().getMarket());
        assertEquals(LocalDate.of(2018, 3, 17), argument.getValue().getDeliveryDate());
        assertEquals(LocalDate.of(2018, 3, 15), argument.getValue().getLastTradingDate());
        engine.stop(true);

    }
}