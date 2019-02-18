package com.scb.refdata.core.impl;

import com.scb.refdata.core.Product;

import java.time.LocalDate;

public class LMEProduct implements Product {
    private String id;

    private LocalDate lastTradingDate;
    private LocalDate deliveryDate;
    private String market;
    private String label;

    public LocalDate getLastTradingDate() {
        return lastTradingDate;
    }

    public void setLastTradingDate(LocalDate lastTradingDate) {
        this.lastTradingDate = lastTradingDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LMEProduct{" +
                "id='" + id + '\'' +
                ", lastTradingDate=" + lastTradingDate +
                ", deliveryDate=" + deliveryDate +
                ", market='" + market + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
