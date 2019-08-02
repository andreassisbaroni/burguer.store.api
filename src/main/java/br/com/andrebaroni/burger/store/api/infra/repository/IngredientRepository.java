package br.com.andrebaroni.burger.store.api.infra.repository;

import br.com.andrebaroni.burger.store.api.domain.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.UUID;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, UUID>, Serializable {
}
