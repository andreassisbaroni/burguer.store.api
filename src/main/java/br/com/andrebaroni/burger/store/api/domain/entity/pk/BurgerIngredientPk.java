package br.com.andrebaroni.burger.store.api.domain.entity.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class BurgerIngredientPk implements Serializable {

    @Column(name = "fk_burger")
    private UUID fkBurger;

    @Column(name = "fk_ingredient")
    private UUID fkIngredient;

    private BurgerIngredientPk() {
        super();
    }

    public BurgerIngredientPk(UUID fkBurger, UUID fkIngredient) {
        this();
        this.setFkBurger(fkBurger);
        this.setFkIngredient(fkIngredient);
    }

    public UUID getFkBurger() {
        return fkBurger;
    }

    public void setFkBurger(UUID fkBurger) {
        this.fkBurger = fkBurger;
    }

    public UUID getFkIngredient() {
        return fkIngredient;
    }

    public void setFkIngredient(UUID fkIngredient) {
        this.fkIngredient = fkIngredient;
    }
}
