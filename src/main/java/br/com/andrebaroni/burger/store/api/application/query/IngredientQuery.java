package br.com.andrebaroni.burger.store.api.application.query;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public class IngredientQuery implements Serializable {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private Double price;

    private IngredientQuery() {
        super();
    }

    public IngredientQuery(UUID id, String description, Double price) {
        this();
        this.setId(id);
        this.setDescription(description);
        this.setPrice(price);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
