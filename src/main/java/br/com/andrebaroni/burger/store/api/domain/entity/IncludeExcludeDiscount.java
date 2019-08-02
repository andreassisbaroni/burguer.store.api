package br.com.andrebaroni.burger.store.api.domain.entity;

import javax.persistence.*;

@Entity
@Table
public class IncludeExcludeDiscount extends Discount {

    @ManyToOne
    @JoinColumn(name = "fk_ingredient_required", referencedColumnName = "id")
    private Ingredient ingredientRequired;

    @ManyToOne
    @JoinColumn(name = "fk_ingredient_excluded", referencedColumnName = "id")
    private Ingredient ingredientExcluded;

    @Column
    private Double discountPercentage;

    private IncludeExcludeDiscount() {
        super();
    }

    public IncludeExcludeDiscount(Ingredient ingredientRequired, Ingredient ingredientExcluded, Double discountPercentage) {
        this();
        this.setIngredientRequired(ingredientRequired);
        this.setIngredientExcluded(ingredientExcluded);
        this.setDiscountPercentage(discountPercentage);
    }

    public Ingredient getIngredientRequired() {
        return ingredientRequired;
    }

    public void setIngredientRequired(Ingredient ingredientRequired) {
        this.ingredientRequired = ingredientRequired;
    }

    public Ingredient getIngredientExcluded() {
        return ingredientExcluded;
    }

    public void setIngredientExcluded(Ingredient ingredientExcluded) {
        this.ingredientExcluded = ingredientExcluded;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
