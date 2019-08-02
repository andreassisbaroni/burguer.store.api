package br.com.andrebaroni.burger.store.api.domain.service;

import br.com.andrebaroni.burger.store.api.application.query.IngredientQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public interface IngredientService extends Serializable {

    Page<IngredientQuery> findIngredients(Pageable pageable);
}
