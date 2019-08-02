package br.com.andrebaroni.burger.store.api.application.query;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public class SaleItemQuery implements Serializable {

    @JsonProperty("idSale")
    private UUID idSale;

    @JsonProperty("idItem")
    private UUID idItem;

    @JsonProperty("burger")
    private String burger;

    @JsonProperty("price")
    private Double price;

    private SaleItemQuery() {
        super();
    }

    public SaleItemQuery(UUID idSale, UUID idItem, String burger, Double price) {
        this();
        this.setIdSale(idSale);
        this.setIdItem(idItem);
        this.setBurger(burger);
        this.setPrice(price);
    }

    public UUID getIdSale() {
        return idSale;
    }

    public void setIdSale(UUID idSale) {
        this.idSale = idSale;
    }

    public UUID getIdItem() {
        return idItem;
    }

    public void setIdItem(UUID idItem) {
        this.idItem = idItem;
    }

    public String getBurger() {
        return burger;
    }

    public void setBurger(String burger) {
        this.burger = burger;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
