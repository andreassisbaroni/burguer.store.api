package br.com.andrebaroni.burger.store.api.application.command;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class CreateSaleSaleItemCommand implements Serializable {

    @JsonProperty("idBurger")
    @NotNull
    @NotBlank
    private UUID idBurger;

    @JsonProperty("ingredients")
    @NotNull
    @NotEmpty
    private Collection<CreateSaleItemIngredientCommand> ingredients;

    public CreateSaleSaleItemCommand() {
        super();
        this.setIngredients(new ArrayList<>());
    }

    public CreateSaleSaleItemCommand(UUID idBurger) {
        this();
        this.setIdBurger(idBurger);
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
