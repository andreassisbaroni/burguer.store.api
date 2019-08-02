package br.com.andrebaroni.burger.store.api.domain.service.impl;

import br.com.andrebaroni.burger.store.api.application.query.BurgerQuery;
import br.com.andrebaroni.burger.store.api.domain.entity.Burger;
import br.com.andrebaroni.burger.store.api.domain.service.BurgerService;
import br.com.andrebaroni.burger.store.api.infra.repository.BurgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BurgerServiceImpl implements BurgerService {

    private final BurgerRepository burgerRepository;

    @Autowired
    public BurgerServiceImpl(BurgerRepository burgerRepository) {
        this.burgerRepository = burgerRepository;
    }

    @Override
    public BurgerQuery findById(UUID id) {
        Burger burger = this.burgerRepository.findById(id).orElseThrow(RuntimeException::new);
        return new BurgerQuery(burger.getId(), burger.getDescription(), burger.getPrice());
    }

    @Override
    public Page<BurgerQuery> findMenuBurgers(String description, Pageable pageable) {
        return new PageImpl<>(this.burgerRepository.findByDescriptionContainingIgnoreCaseAndMenuBurgerOrderByDescription(description, Boolean.TRUE, pageable)
                .getContent()
                .parallelStream()
                .map(burger -> new BurgerQuery(burger.getId(), burger.getDescription(), burger.getPrice()))
                .collect(Collectors.toList()));
    }
}
