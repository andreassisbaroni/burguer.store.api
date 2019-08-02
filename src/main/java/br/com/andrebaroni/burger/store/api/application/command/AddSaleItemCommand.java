package br.com.andrebaroni.burger.store.api.application.command;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class AddSaleItemCommand {

    @JsonProperty("idSale")
    @NotNull
    @NotBlank
    private UUID idSale;

    @JsonProperty("idBurger")
    @NotNull
    @NotBlank
    private UUID idBurger;

    @JsonProperty("ingredients")
    private Collection<CreateSaleItemIngredientCommand> ingredients;

    private AddSaleItemCommand() {
        super();
        this.setIngredients(new ArrayList<>());
    }

    public AddSaleItemCommand(UUID idSale, UUID idBurger, Collection<CreateSaleItemIngredientCommand> ingredients) {
        this();
        this.setIdSale(idSale);
        this.setIdBurger(idBurger);
        this.setIngredients(ingredients);
    }

    public UUID getIdSale() {
        return idSale;
    }

    public void setIdSale(UUID idSale) {
        this.idSale = idSale;
    }

    public UUID getIdBurger() {
        return idBurger;
    }

    public void setIdBurger(UUID idBurger) {
        this.idBurger = idBurger;
    }

    public Collection<CreateSaleItemIngredientCommand> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<CreateSaleItemIngredientCommand> ingredients) {
        this.ingredients = ingredients;
    }
}
