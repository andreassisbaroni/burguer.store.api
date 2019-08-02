package br.com.andrebaroni.burger.store.api.application.controller;

import br.com.andrebaroni.burger.store.api.application.query.BurgerIngredientQuery;
import br.com.andrebaroni.burger.store.api.application.query.SaleItemQuery;
import br.com.andrebaroni.burger.store.api.domain.service.SaleItemService;
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
@RequestMapping("/api/sales/{idSale}/items")
public class SaleItemController implements Serializable {

    private final SaleItemService saleItemService;

    public SaleItemController(SaleItemService saleItemService) {
        this.saleItemService = saleItemService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<SaleItemQuery>> findBySale(@PathVariable(value = "idSale") final UUID idSale,
                                                          Pageable pageable) {
        return new ResponseEntity<>(this.saleItemService.findBySale(idSale, pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/{idItem}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<BurgerIngredientQuery>> findIngredientByItem(@PathVariable(value = "idSale") final UUID idSale,
                                                                            @PathVariable(value = "idItem") final UUID idItem,
                                                                            Pageable pageable) {
        return new ResponseEntity<>(this.saleItemService.findIngredientFromItem(idSale, idItem, pageable), HttpStatus.OK);
    }
}
