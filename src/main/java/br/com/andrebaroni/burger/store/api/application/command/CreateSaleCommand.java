package br.com.andrebaroni.burger.store.api.application.command;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class CreateSaleCommand implements Serializable {

    @JsonProperty("burgers")
    @NotNull
    @NotEmpty
    private Collection<CreateSaleSaleItemCommand> items;

    public CreateSaleCommand() {
        super();
        this.setItems(new ArrayList<>());
    }

    public CreateSaleCommand(Collection<CreateSaleSaleItemCommand> items) {
        this();
        this.setItems(items);
    }

    public Collection<CreateSaleSaleItemCommand> getItems() {
        return items;
    }

    public void setItems(Collection<CreateSaleSaleItemCommand> items) {
        this.items = items;
    }
}
