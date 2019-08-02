package br.com.andrebaroni.burger.store.api.application.command;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class CreateSaleItemIngredientCommand implements Serializable {

    @JsonProperty("idIngredient")
    @NotNull
    @NotBlank
    private UUID idIngredient;

    @JsonProperty("amount")
    @NotNull
    @Min(1)
    private Integer amount;

    private CreateSaleItemIngredientCommand() {
        super();
    }

    public CreateSaleItemIngredientCommand(UUID idIngredient, Integer amount) {
        this();
        this.setIdIngredient(idIngredient);
        this.setAmount(amount);
    }

    public UUID getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(UUID idIngredient) {
        this.idIngredient = idIngredient;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
