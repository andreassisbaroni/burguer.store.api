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

    private IncludeExcludeDiscount(String description) {
        super(description);
    }

    private IncludeExcludeDiscount(String description, boolean active) {
        super(description, active);
    }

    public IncludeExcludeDiscount(String description, Ingredient ingredientRequired, Ingredient ingredientExcluded, Double discountPercentage) {
        this(description);
        this.setIngredientRequired(ingredientRequired);
        this.setIngredientExcluded(ingredientExcluded);
        this.setDiscountPercentage(discountPercentage);
    }

    public IncludeExcludeDiscount(String description, boolean active, Ingredient ingredientRequired, Ingredient ingredientExcluded, Double discountPercentage) {
        this(description, active);
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

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
