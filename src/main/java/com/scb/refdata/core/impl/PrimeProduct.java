package com.scb.refdata.core.impl;

public class PrimeProduct extends LMEProduct {
    private String exchangeCode;
    private boolean tradable;

    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public boolean isTradable() {
        return tradable;
    }

    public void setTradable(boolean tradable) {
        this.tradable = tradable;
    }

    @Override
    public String toString() {
        return "PrimeProduct{" +
                "exchangeCode='" + exchangeCode + '\'' +
                ", tradable=" + tradable +
                "} " + super.toString();
    }
}
