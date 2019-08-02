package br.com.andrebaroni.burger.store.api.infra.repository;

import br.com.andrebaroni.burger.store.api.domain.entity.Burger;
import br.com.andrebaroni.burger.store.api.domain.entity.BurgerIngredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.UUID;

@Repository
public interface BurgerIngredientRepository extends JpaRepository<BurgerIngredient, UUID>, Serializable {

    Page<BurgerIngredient> findByBurgerOrderByBurger(Burger burger, Pageable pageable);
}
