package br.com.andrebaroni.burger.store.api.domain.entity;

import br.com.andrebaroni.burger.store.api.domain.entity.pk.BurgerIngredientPk;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table
public class BurgerIngredient implements Serializable {

    @EmbeddedId
    private BurgerIngredientPk pk;

    @ManyToOne
    @JoinColumn(name = "fk_burger", referencedColumnName = "id", unique = true, insertable = false, updatable = false)
    private Burger burger;

    @ManyToOne
    @JoinColumn(name = "fk_ingredient", referencedColumnName = "id", unique = true, insertable = false, updatable = false)
    private Ingredient ingredient;

    @Column
    private Integer amount;

    @Column
    private Integer amountDiscount;

    @Column
    private Double discountPercentage;

    private BurgerIngredient() {
        super();
        this.setAmountDiscount(0);
        this.setDiscountPercentage(0.0);
    }

    public BurgerIngredient(Burger burger, Ingredient ingredient, Integer amount) {
        this();
        this.setPk(new BurgerIngredientPk(burger.getId(), ingredient.getId()));
        this.setBurger(burger);
        this.setIngredient(ingredient);
        this.setAmount(amount);
    }

    public BurgerIngredientPk getPk() {
        return pk;
    }

    public void setPk(BurgerIngredientPk pk) {
        this.pk = pk;
    }

    public Burger getBurger() {
        return burger;
    }

    public void setBurger(Burger burger) {
        this.burger = burger;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmountDiscount() {
        return amountDiscount;
    }

    public void setAmountDiscount(Integer amountDiscount) {
        this.amountDiscount = amountDiscount;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getIngredientDescription() {
        if (Objects.nonNull(this.getIngredient())) {
            return this.getIngredient().getDescription();
        }

        return null;
    }

    public UUID getIdBurger() {
        if (Objects.nonNull(this.getBurger())) {
            return this.getBurger().getId();
        }

        return null;
    }

    public UUID getIdIngredient() {
        if (Objects.nonNull(this.getIngredient())) {
            return this.getIngredient().getId();
        }

        return null;
    }

    public Double getPrice() {
        double finalPrice = 0.0;

        if (Objects.nonNull(this.getIngredient())) {
            finalPrice += this.getAmount() * this.getIngredient().getPrice();

            if (Objects.nonNull(this.getAmountDiscount())) {
                finalPrice -= this.getAmountDiscount() * this.getIngredient().getPrice();
            }
        }

        return finalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BurgerIngredient)) return false;
        BurgerIngredient that = (BurgerIngredient) o;
        return Objects.equals(getPk(), that.getPk());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPk());
    }
}
