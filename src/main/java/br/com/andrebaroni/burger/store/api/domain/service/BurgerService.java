package br.com.andrebaroni.burger.store.api.domain.service;

import br.com.andrebaroni.burger.store.api.application.query.BurgerQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.UUID;

public interface BurgerService extends Serializable {

    BurgerQuery findById(UUID id);

    Page<BurgerQuery> findMenuBurgers(String description, Pageable pageable);
}
