package br.com.andrebaroni.burger.store.api.domain.service;

import br.com.andrebaroni.burger.store.api.application.query.BurgerIngredientQuery;
import br.com.andrebaroni.burger.store.api.domain.entity.BurgerIngredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.UUID;

public interface BurgerIngredientService extends Serializable {

    Page<BurgerIngredientQuery> findByBurger(UUID idBurger, Pageable pageable);

    BurgerIngredient save(BurgerIngredient burgerIngredient);
}
