package br.com.andrebaroni.burger.store.api.domain.service;

import br.com.andrebaroni.burger.store.api.application.command.CreateSaleCommand;
import br.com.andrebaroni.burger.store.api.application.query.SaleQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.UUID;

public interface SaleService extends Serializable {

    Page<SaleQuery> findAll(Pageable pageable);

    SaleQuery create(CreateSaleCommand createSaleCommand);

    SaleQuery finishSale(UUID id);

    SaleQuery cancelSale(UUID id);
}
