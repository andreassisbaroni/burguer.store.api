package br.com.andrebaroni.burger.store.api.domain.service;

import br.com.andrebaroni.burger.store.api.application.command.AddSaleItemCommand;
import br.com.andrebaroni.burger.store.api.application.query.BurgerIngredientQuery;
import br.com.andrebaroni.burger.store.api.application.query.SaleItemQuery;
import br.com.andrebaroni.burger.store.api.domain.entity.SaleItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.UUID;

public interface SaleItemService extends Serializable {

    Page<SaleItemQuery> findBySale(UUID idSale, Pageable pageable);

    SaleItem addNewSaleItemToSale(AddSaleItemCommand addSaleItemCommand);

    Page<BurgerIngredientQuery> findIngredientFromItem(UUID idSale, UUID idItem, Pageable pageable);
}
