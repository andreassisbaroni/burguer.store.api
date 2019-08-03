package br.com.andrebaroni.burger.store.api.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Table
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Discount implements Serializable {

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
    private Boolean active;

    @ManyToMany(mappedBy = "discounts")
    private Collection<Burger> burgers;

    protected Discount() {
        super();
        this.setActive(false);
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Collection<Burger> getBurgers() {
        return burgers;
    }

    public void setBurgers(Collection<Burger> burgers) {
        this.burgers = burgers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount)) return false;
        Discount discount = (Discount) o;
        return Objects.equals(getId(), discount.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
