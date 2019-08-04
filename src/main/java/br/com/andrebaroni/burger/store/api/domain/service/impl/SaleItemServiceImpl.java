package br.com.andrebaroni.burger.store.api.domain.service.impl;

import br.com.andrebaroni.burger.store.api.application.command.AddSaleItemCommand;
import br.com.andrebaroni.burger.store.api.application.command.CreateSaleItemIngredientCommand;
import br.com.andrebaroni.burger.store.api.application.query.BurgerIngredientQuery;
import br.com.andrebaroni.burger.store.api.application.query.SaleItemQuery;
import br.com.andrebaroni.burger.store.api.domain.entity.*;
import br.com.andrebaroni.burger.store.api.domain.exception.EntityNotFoundException;
import br.com.andrebaroni.burger.store.api.domain.service.BurgerIngredientService;
import br.com.andrebaroni.burger.store.api.domain.service.DiscountService;
import br.com.andrebaroni.burger.store.api.domain.service.SaleItemService;
import br.com.andrebaroni.burger.store.api.infra.repository.BurgerRepository;
import br.com.andrebaroni.burger.store.api.infra.repository.IngredientRepository;
import br.com.andrebaroni.burger.store.api.infra.repository.SaleItemRepository;
import br.com.andrebaroni.burger.store.api.infra.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    private final DiscountService discountService;

    @Autowired
    public SaleItemServiceImpl(SaleRepository saleRepository, SaleItemRepository saleItemRepository,
                               BurgerRepository burgerRepository, IngredientRepository ingredientRepository,
                               BurgerIngredientService burgerIngredientService, DiscountService discountService) {
        this.saleRepository = saleRepository;
        this.saleItemRepository = saleItemRepository;
        this.burgerRepository = burgerRepository;
        this.ingredientRepository = ingredientRepository;
        this.burgerIngredientService = burgerIngredientService;
        this.discountService = discountService;
    }

    @Override
    public Page<SaleItemQuery> findBySale(UUID idSale, Pageable pageable) {
        return new PageImpl<>(this.saleItemRepository.findBySaleOrderByIdDesc(
                this.saleRepository.findById(idSale).orElseThrow(() -> new EntityNotFoundException(Sale.class)), pageable
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

        this.applyDiscountsToBurger(burger);

        return this.saleItemRepository.save(new SaleItem(
                this.saleRepository.findById(addSaleItemCommand.getIdSale()).orElseThrow(() -> new EntityNotFoundException(Sale.class)),
                burger
        ));
    }

    public Page<BurgerIngredientQuery> findIngredientFromItem(UUID idSale, UUID idItem, Pageable pageable) {
        SaleItem saleItem = this.saleItemRepository.findById(idItem).orElseThrow(() -> new EntityNotFoundException(SaleItem.class));
        if (!saleItem.getSale().getId().equals(idSale)) {
            throw new RuntimeException();
        }

        return this.burgerIngredientService.findByBurger(saleItem.getIdBurger(), pageable);
    }

    private Burger createNewBurgerToThisItem(UUID idBurger) {
        Burger currentBurger = this.burgerRepository.findById(idBurger).orElseThrow(() -> new EntityNotFoundException(Burger.class));

        Burger burger = new Burger(currentBurger.getDescription());

        return this.burgerRepository.save(burger);
    }

    private void createRelationshipWithBurgerAndIngredients(Burger burger, Collection<CreateSaleItemIngredientCommand> createSaleItemIngredients) {
        createSaleItemIngredients.forEach(
                createSaleItemIngredientCommand -> {
                    Ingredient ingredient = this.ingredientRepository.findById(createSaleItemIngredientCommand.getIdIngredient()).orElseThrow(() -> new EntityNotFoundException(Ingredient.class));
                    BurgerIngredient burgerIngredient = new BurgerIngredient(burger, ingredient, createSaleItemIngredientCommand.getAmount());
                    this.burgerIngredientService.save(burgerIngredient);
                    burger.addIngredient(burgerIngredient);
                }
        );
    }

    private void applyDiscountsToBurger(Burger burger) {
        Collection<Discount> discounts = this.discountService.findAllActive();

        if (!CollectionUtils.isEmpty(discounts)) {
            discounts.forEach(burger::applyDiscount);
        }

        burger.getBurgerIngredients().forEach(this.burgerIngredientService::save);

        this.burgerRepository.save(burger);
    }
}
