package br.com.andrebaroni.burger.store.api.domain.service.impl;

import br.com.andrebaroni.burger.store.api.application.query.IngredientQuery;
import br.com.andrebaroni.burger.store.api.domain.service.IngredientService;
import br.com.andrebaroni.burger.store.api.infra.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Page<IngredientQuery> findIngredients(Pageable pageable) {
        return new PageImpl<>(this.ingredientRepository.findAll(pageable)
                .getContent()
                .parallelStream()
                .map(ingredient -> new IngredientQuery(ingredient.getId(), ingredient.getDescription(), ingredient.getPrice()))
                .collect(Collectors.toList()));
    }

}
