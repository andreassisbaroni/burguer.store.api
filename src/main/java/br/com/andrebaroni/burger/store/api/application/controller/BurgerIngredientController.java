package br.com.andrebaroni.burger.store.api.application.controller;

import br.com.andrebaroni.burger.store.api.application.query.BurgerIngredientQuery;
import br.com.andrebaroni.burger.store.api.domain.service.BurgerIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/burgers/{idBurger}/ingredients")
public class BurgerIngredientController implements Serializable {

    private final BurgerIngredientService burgerIngredientService;

    @Autowired
    public BurgerIngredientController(BurgerIngredientService burgerIngredientService) {
        this.burgerIngredientService = burgerIngredientService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<BurgerIngredientQuery>> findByBurger(@PathVariable(value = "idBurger") final UUID idBurger,
                                                                    Pageable pageable) {
        return new ResponseEntity<>(this.burgerIngredientService.findByBurger(idBurger, pageable), HttpStatus.OK);
    }
}
