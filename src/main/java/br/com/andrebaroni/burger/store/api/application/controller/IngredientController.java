package br.com.andrebaroni.burger.store.api.application.controller;

import br.com.andrebaroni.burger.store.api.application.query.IngredientQuery;
import br.com.andrebaroni.burger.store.api.domain.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping(value = "/api/ingredients")
public class IngredientController implements Serializable {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<IngredientQuery>> findIngredients(Pageable pageable) {
        return new ResponseEntity<>(this.ingredientService.findIngredients(pageable), HttpStatus.OK);
    }
}
