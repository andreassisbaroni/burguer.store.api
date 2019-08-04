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

    private PortionDiscount(String description) {
        super(description);
    }

    private PortionDiscount(String description, boolean active) {
        super(description, active);
    }

    public PortionDiscount(String description, Ingredient ingredient, Integer amountRequested, Integer amountDiscount) {
        this(description);
        this.setIngredient(ingredient);
        this.setAmountRequested(amountRequested);
        this.setAmountDiscount(amountDiscount);
    }

    public PortionDiscount(String description, boolean active, Ingredient ingredient, Integer amountRequested,
                           Integer amountDiscount) {
        this(description, active);
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

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
