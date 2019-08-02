package br.com.andrebaroni.burger.store.api.domain.service.impl;

import br.com.andrebaroni.burger.store.api.application.query.BurgerIngredientQuery;
import br.com.andrebaroni.burger.store.api.domain.entity.Burger;
import br.com.andrebaroni.burger.store.api.domain.entity.BurgerIngredient;
import br.com.andrebaroni.burger.store.api.domain.service.BurgerIngredientService;
import br.com.andrebaroni.burger.store.api.infra.repository.BurgerIngredientRepository;
import br.com.andrebaroni.burger.store.api.infra.repository.BurgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BurgerIngredientServiceImpl implements BurgerIngredientService {

    private final BurgerRepository burgerRepository;
    private final BurgerIngredientRepository burgerIngredientRepository;

    @Autowired
    public BurgerIngredientServiceImpl(BurgerRepository burgerRepository, BurgerIngredientRepository burgerIngredientRepository) {
        this.burgerRepository = burgerRepository;
        this.burgerIngredientRepository = burgerIngredientRepository;
    }

    @Override
    public Page<BurgerIngredientQuery> findByBurger(UUID idBurger, Pageable pageable) {
        Burger burger = this.burgerRepository.findById(idBurger).orElseThrow(EntityNotFoundException::new);

        return new PageImpl<>(this.burgerIngredientRepository.findByBurgerOrderByBurger(burger, pageable)
                .getContent()
                .parallelStream()
                .map(burgerIngredient -> new BurgerIngredientQuery(burgerIngredient.getIdBurger(),
                        burgerIngredient.getIdIngredient(),
                        burgerIngredient.getIngredientDescription(),
                        burgerIngredient.getAmount()))
                .collect(Collectors.toList())
        );
    }

    @Override
    public BurgerIngredient save(BurgerIngredient burgerIngredient){
        return this.burgerIngredientRepository.save(burgerIngredient);
    }
}
