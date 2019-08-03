package br.com.andrebaroni.burger.store.api.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table
public class SaleItem implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "fk_sale", referencedColumnName = "id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "fk_burger", referencedColumnName = "id")
    private Burger burger;

    private SaleItem() {
        super();
    }

    public SaleItem(Sale sale, Burger burger) {
        this();
        this.setSale(sale);
        this.setBurger(burger);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Burger getBurger() {
        return burger;
    }

    public void setBurger(Burger burger) {
        this.burger = burger;
    }

    public UUID getIdBurger() {
        if (Objects.nonNull(this.getBurger())) {
            return this.getBurger().getId();
        }

        return null;
    }

    public String getBurgerDescription() {
        if (Objects.nonNull(this.getBurger())) {
            return this.getBurger().getDescription();
        }

        return null;
    }

    public Double getPrice() {
        if (Objects.nonNull(this.getBurger())) {
            return this.getBurger().getPrice();
        }

        return 0.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaleItem)) return false;
        SaleItem saleItem = (SaleItem) o;
        return Objects.equals(getId(), saleItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
