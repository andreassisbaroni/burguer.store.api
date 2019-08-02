package br.com.andrebaroni.burger.store.api.application.controller;

import br.com.andrebaroni.burger.store.api.application.query.BurgerQuery;
import br.com.andrebaroni.burger.store.api.domain.service.BurgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/burgers")
public class BurgerController implements Serializable {

    private final BurgerService burgerService;

    @Autowired
    public BurgerController(BurgerService burgerService) {
        this.burgerService = burgerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<BurgerQuery>> findBurgers(@RequestParam(value = "description", defaultValue = "") final String description,
                                                          Pageable pageable) {
        return new ResponseEntity<>(this.burgerService.findMenuBurgers(description, pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BurgerQuery> findById(@PathVariable(value = "id") final UUID idBurger) {
        return new ResponseEntity<>(this.burgerService.findById(idBurger), HttpStatus.OK);
    }
}
