package br.com.andrebaroni.burger.store.api.application.controller;

import br.com.andrebaroni.burger.store.api.application.command.CreateSaleCommand;
import br.com.andrebaroni.burger.store.api.application.query.SaleQuery;
import br.com.andrebaroni.burger.store.api.domain.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping(value = "/api/sales")
public class SaleController implements Serializable {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<SaleQuery>> findAll(Pageable pageable) {
        return new ResponseEntity<>(this.saleService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaleQuery> create(@RequestBody CreateSaleCommand createSaleCommand) {
        return new ResponseEntity<>(this.saleService.create(createSaleCommand), HttpStatus.CREATED);
    }
}
