package br.com.andrebaroni.burger.store.api.application.query;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public class BurgerIngredientQuery implements Serializable {

    @JsonProperty("idBurger")
    private UUID idBurger;

    @JsonProperty("idIngredient")
    private UUID idIngredient;

    @JsonProperty("ingredient")
    private String ingredient;

    @JsonProperty("amount")
    private Integer amount;

    private BurgerIngredientQuery() {
        super();
    }

    public BurgerIngredientQuery(UUID idBurger, UUID idIngredient, String ingredient, Integer amount) {
        this();
        this.setIdBurger(idBurger);
        this.setIdIngredient(idIngredient);
        this.setIngredient(ingredient);
        this.setAmount(amount);
    }

    public UUID getIdBurger() {
        return idBurger;
    }

    public void setIdBurger(UUID idBurger) {
        this.idBurger = idBurger;
    }

    public UUID getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(UUID idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
