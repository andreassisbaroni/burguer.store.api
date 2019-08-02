package br.com.andrebaroni.burger.store.api.domain.entity;

import javax.persistence.*;

@Entity
@Table
public class PortionDiscount extends Discount {

    @ManyToOne
    @JoinColumn(name = "fk_ingredient", referencedColumnName = "id")
    private Ingredient ingredient;

    @Column
    private Integer amountRequested;

    @Column
    private Integer amountDiscount;

    private PortionDiscount() {
        super();
    }

    public PortionDiscount(Ingredient ingredient, Integer amountRequested, Integer amountDiscount) {
        this();
        this.setIngredient(ingredient);
        this.setAmountRequested(amountRequested);
        this.setAmountDiscount(amountDiscount);
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getAmountRequested() {
        return amountRequested;
    }

    public void setAmountRequested(Integer amountRequested) {
        this.amountRequested = amountRequested;
    }

    public Integer getAmountDiscount() {
        return amountDiscount;
    }

    public void setAmountDiscount(Integer amountDiscount) {
        this.amountDiscount = amountDiscount;
    }
}
