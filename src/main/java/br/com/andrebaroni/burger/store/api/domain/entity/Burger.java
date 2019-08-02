package br.com.andrebaroni.burger.store.api.domain.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table
public class Burger implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column
    private String description;

    @Column(precision = 12, scale = 2)
    private Double price;

    @Column
    private Boolean menuBurger;

    @OneToMany(mappedBy = "burger")
    private Collection<BurgerIngredient> burgerIngredients;

    @OneToMany(mappedBy = "burger")
    private Collection<SaleItem> saleItems;

    private Burger() {
        super();
        this.setMenuBurger(false);
    }

    public Burger(String description) {
        this();
        this.setDescription(description);
    }

    public Burger(String description, Double price) {
        this();
        this.setDescription(description);
        this.setPrice(price);
    }

    public Burger(String description, Double price, Boolean menuBurger) {
        this();
        this.setDescription(description);
        this.setPrice(price);
        this.setMenuBurger(menuBurger);
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

    public Boolean getMenuBurger() {
        return menuBurger;
    }

    public void setMenuBurger(Boolean menuBurger) {
        this.menuBurger = menuBurger;
    }

    public Collection<BurgerIngredient> getBurgerIngredients() {
        return burgerIngredients;
    }

    public void setBurgerIngredients(Collection<BurgerIngredient> burgerIngredients) {
        this.burgerIngredients = burgerIngredients;
    }

    public Collection<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(Collection<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public void addIngredient(BurgerIngredient burgerIngredient) {
        if (Objects.isNull(this.getBurgerIngredients())) {
            this.setBurgerIngredients(new ArrayList<>());
        }

        this.getBurgerIngredients().add(burgerIngredient);
    }

    public void calculatePrice() {
        if (!CollectionUtils.isEmpty(this.getBurgerIngredients())) {
            this.setPrice(this.getBurgerIngredients().parallelStream().mapToDouble(BurgerIngredient::getPrice).sum());
        } else {
            this.setPrice(0.0);
        }
    }
}
