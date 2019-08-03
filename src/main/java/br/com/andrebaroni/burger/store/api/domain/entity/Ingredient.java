package br.com.andrebaroni.burger.store.api.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table
public class Ingredient implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column
    private String description;

    @Column
    private Double price;

    @OneToMany(mappedBy = "ingredient")
    private Collection<BurgerIngredient> burgerIngredients;

    private Ingredient() {
        super();
    }

    public Ingredient(String description, Double price) {
        this();
        this.setDescription(description);
        this.setPrice(price);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Collection<BurgerIngredient> getBurgerIngredients() {
        return burgerIngredients;
    }

    public void setBurgerIngredients(Collection<BurgerIngredient> burgerIngredients) {
        this.burgerIngredients = burgerIngredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
