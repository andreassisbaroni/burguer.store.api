package br.com.andrebaroni.burger.store.api.domain.service.impl;

import br.com.andrebaroni.burger.store.api.application.command.AddSaleItemCommand;
import br.com.andrebaroni.burger.store.api.application.command.CreateSaleCommand;
import br.com.andrebaroni.burger.store.api.application.query.SaleQuery;
import br.com.andrebaroni.burger.store.api.domain.entity.Sale;
import br.com.andrebaroni.burger.store.api.domain.service.SaleItemService;
import br.com.andrebaroni.burger.store.api.domain.service.SaleService;
import br.com.andrebaroni.burger.store.api.infra.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleItemService saleItemService;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, SaleItemService saleItemService) {
        this.saleRepository = saleRepository;
        this.saleItemService = saleItemService;
    }

    @Override
    public Page<SaleQuery> findAll(Pageable pageable) {
        return new PageImpl<>(
                this.saleRepository.findAll(pageable)
                        .getContent()
                        .parallelStream()
                        .map(sale -> new SaleQuery(sale.getId(), sale.getSaleDate(), sale.getFinishDate(), sale.getCancelDate(), sale.getStatus(), sale.getPrice()))
                        .collect(Collectors.toList())
        );
    }

    @Override
    @Transactional
    public SaleQuery create(CreateSaleCommand createSaleCommand) {
        Sale sale = new Sale();
        this.saleRepository.save(sale);

        if (!CollectionUtils.isEmpty(createSaleCommand.getItems())) {
            sale.setSaleItems(createSaleCommand.getItems().stream().map(
                    createSaleSaleItemCommand -> {
                        AddSaleItemCommand addSaleItemCommand = new AddSaleItemCommand(sale.getId(),
                                createSaleSaleItemCommand.getIdBurger(), createSaleSaleItemCommand.getIngredients());
                        return this.saleItemService.addNewSaleItemToSale(addSaleItemCommand);
                    }
            ).collect(Collectors.toList()));
        }

        sale.calculatePrice();
        this.saleRepository.save(sale);

        return new SaleQuery(sale.getId(), sale.getSaleDate(), sale.getFinishDate(), sale.getCancelDate(), sale.getStatus(), sale.getPrice());
    }
}
