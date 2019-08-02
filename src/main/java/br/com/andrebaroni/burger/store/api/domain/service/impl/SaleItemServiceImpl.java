package br.com.andrebaroni.burger.store.api.domain.service.impl;

import br.com.andrebaroni.burger.store.api.application.command.AddSaleItemCommand;
import br.com.andrebaroni.burger.store.api.application.command.CreateSaleItemIngredientCommand;
import br.com.andrebaroni.burger.store.api.application.query.BurgerIngredientQuery;
import br.com.andrebaroni.burger.store.api.application.query.SaleItemQuery;
import br.com.andrebaroni.burger.store.api.domain.entity.Burger;
import br.com.andrebaroni.burger.store.api.domain.entity.BurgerIngredient;
import br.com.andrebaroni.burger.store.api.domain.entity.Ingredient;
import br.com.andrebaroni.burger.store.api.domain.entity.SaleItem;
import br.com.andrebaroni.burger.store.api.domain.service.BurgerIngredientService;
import br.com.andrebaroni.burger.store.api.domain.service.SaleItemService;
import br.com.andrebaroni.burger.store.api.infra.repository.*;
import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SaleItemServiceImpl implements SaleItemService {

    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final BurgerRepository burgerRepository;
    private final IngredientRepository ingredientRepository;
    private final BurgerIngredientService burgerIngredientService;

    @Autowired
    public SaleItemServiceImpl(SaleRepository saleRepository, SaleItemRepository saleItemRepository,
                               BurgerRepository burgerRepository, IngredientRepository ingredientRepository,
                               BurgerIngredientService burgerIngredientService) {
        this.saleRepository = saleRepository;
        this.saleItemRepository = saleItemRepository;
        this.burgerRepository = burgerRepository;
        this.ingredientRepository = ingredientRepository;
        this.burgerIngredientService = burgerIngredientService;
    }

    @Override
    public Page<SaleItemQuery> findBySale(UUID idSale, Pageable pageable) {
        return new PageImpl<>(this.saleItemRepository.findBySaleOrderByIdDesc(
                this.saleRepository.findById(idSale).orElseThrow(EntityExistsException::new), pageable
        ).getContent().parallelStream()
                .map(saleItem -> new SaleItemQuery(idSale,
                        saleItem.getId(),
                        saleItem.getBurgerDescription(),
                        saleItem.getPrice()))
                .collect(Collectors.toList())
        );
    }

    @Override
    @Transactional
    public SaleItem addNewSaleItemToSale(AddSaleItemCommand addSaleItemCommand) {
        Burger burger = this.createNewBurgerToThisItem(addSaleItemCommand.getIdBurger());

        this.createRelationshipWithBurgerAndIngredients(burger, addSaleItemCommand.getIngredients());

        burger.calculatePrice();

        return this.saleItemRepository.save(new SaleItem(
                this.saleRepository.findById(addSaleItemCommand.getIdSale()).orElseThrow(EntityNotFoundException::new),
                burger
        ));
    }

    public Page<BurgerIngredientQuery> findIngredientFromItem(UUID idSale, UUID idItem, Pageable pageable) {
        SaleItem saleItem = this.saleItemRepository.findById(idItem).orElseThrow(EntityNotFoundException::new);
        if (!saleItem.getSale().getId().equals(idSale)) {
            throw new RuntimeException();
        }

        return this.burgerIngredientService.findByBurger(saleItem.getIdBurger(), pageable);
    }

    private Burger createNewBurgerToThisItem(UUID idBurger) {
        Burger currentBurger = this.burgerRepository.findById(idBurger).orElseThrow(EntityNotFoundException::new);

        Burger burger = new Burger(currentBurger.getDescription());
        burger.calculatePrice();

        return this.burgerRepository.save(burger);
    }

    private void createRelationshipWithBurgerAndIngredients(Burger burger, Collection<CreateSaleItemIngredientCommand> createSaleItemIngredients) {
        createSaleItemIngredients.forEach(
                createSaleItemIngredientCommand -> {
                    Ingredient ingredient = this.ingredientRepository.findById(createSaleItemIngredientCommand.getIdIngredient()).orElseThrow(EntityNotFoundException::new);
                    BurgerIngredient burgerIngredient = new BurgerIngredient(burger, ingredient, createSaleItemIngredientCommand.getAmount());
                    this.burgerIngredientService.save(burgerIngredient);
                    burger.addIngredient(burgerIngredient);
                }
        );
    }
}
