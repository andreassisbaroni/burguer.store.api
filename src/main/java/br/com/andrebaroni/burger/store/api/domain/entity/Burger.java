package br.com.andrebaroni.burger.store.api.domain.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table
public class Burger implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column
    private String description;

    @Column(precision = 12, scale = 2)
    private Double price;

    @Column
    private Boolean menuBurger;

    @OneToMany(mappedBy = "burger")
    private Collection<BurgerIngredient> burgerIngredients;

    @OneToMany(mappedBy = "burger")
    private Collection<SaleItem> saleItems;

    @ManyToMany
    @JoinTable(name = "burger_discount",
            joinColumns = @JoinColumn(name = "fk_burger"),
            inverseJoinColumns = @JoinColumn(name = "fk_discount")
    )
    private Collection<Discount> discounts;

    private Burger() {
        super();
        this.setMenuBurger(false);
        this.calculatePrice();
        this.setBurgerIngredients(new ArrayList<>());
        this.setDiscounts(new ArrayList<>());
    }

    public Burger(String description) {
        this();
        this.setDescription(description);
    }

    public Burger(String description, Double price) {
        this();
        this.setDescription(description);
        this.setPrice(price);
    }

    public Burger(String description, Double price, Boolean menuBurger) {
        this();
        this.setDescription(description);
        this.setPrice(price);
        this.setMenuBurger(menuBurger);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getMenuBurger() {
        return menuBurger;
    }

    public void setMenuBurger(Boolean menuBurger) {
        this.menuBurger = menuBurger;
    }

    public Collection<BurgerIngredient> getBurgerIngredients() {
        return burgerIngredients;
    }

    public void setBurgerIngredients(Collection<BurgerIngredient> burgerIngredients) {
        this.burgerIngredients = burgerIngredients;
    }

    public Collection<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(Collection<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public Collection<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Collection<Discount> discounts) {
        this.discounts = discounts;
    }

    public void addIngredient(BurgerIngredient burgerIngredient) {
        if (Objects.isNull(this.getBurgerIngredients())) {
            this.setBurgerIngredients(new ArrayList<>());
        }

        this.getBurgerIngredients().add(burgerIngredient);
        this.calculatePrice();
    }

    public void calculatePrice() {
        if (!CollectionUtils.isEmpty(this.getBurgerIngredients())) {
            this.setPrice(this.getBurgerIngredients().parallelStream().mapToDouble(BurgerIngredient::getPrice).sum());
        } else {
            this.setPrice(0.0);
        }
    }

    public void applyDiscount(Discount discount) {
        if (discount.getActive()) {
            if (discount instanceof IncludeExcludeDiscount) {
                this.applyIncludeExcludeDiscount((IncludeExcludeDiscount) discount);
            } else if (discount instanceof PortionDiscount) {
                this.applyPortionDiscount((PortionDiscount) discount);
            }
        }
        this.calculatePrice();
    }

    private void applyPortionDiscount(PortionDiscount portionDiscount) {
        if (!CollectionUtils.isEmpty(this.getBurgerIngredients())) {
            Optional<BurgerIngredient> optionalBurgerIngredient = this.getBurgerIngredients()
                    .parallelStream()
                    .filter(burgerIngredient ->
                            burgerIngredient.getIngredient().equals(portionDiscount.getIngredient()) &&
                                    burgerIngredient.getAmount().equals(portionDiscount.getAmountRequested())
                    ).findFirst();

            if (optionalBurgerIngredient.isPresent()) {
                BurgerIngredient burgerIngredient = optionalBurgerIngredient.get();
                Integer discountMultiply = Math.floorDiv(burgerIngredient.getAmount(), portionDiscount.getAmountRequested());
                burgerIngredient.setAmountDiscount(discountMultiply * portionDiscount.getAmountDiscount());

                this.updateIngredient(burgerIngredient);
                this.getDiscounts().add(portionDiscount);
            }
        }
    }

    private void applyIncludeExcludeDiscount(IncludeExcludeDiscount includeExcludeDiscount) {
        if (!CollectionUtils.isEmpty(this.getBurgerIngredients())) {
            Optional<BurgerIngredient> optionalBurgerIngredient = this.getBurgerIngredients()
                    .parallelStream()
                    .filter(burgerIngredient ->
                            burgerIngredient.getIngredient().equals(includeExcludeDiscount.getIngredientRequired())
                    ).findFirst();

            if (optionalBurgerIngredient.isPresent()) {
                boolean isApplicable = this.getBurgerIngredients().parallelStream().noneMatch(burgerIngredient ->
                        burgerIngredient.getIngredient().equals(includeExcludeDiscount.getIngredientExcluded())
                );

                if (isApplicable) {
                    BurgerIngredient burgerIngredient = optionalBurgerIngredient.get();

                    burgerIngredient.setDiscountPercentage(includeExcludeDiscount.getDiscountPercentage());

                    this.updateIngredient(burgerIngredient);
                    this.getDiscounts().add(includeExcludeDiscount);
                }
            }
        }
    }

    private void updateIngredient(BurgerIngredient burgerIngredient) {
        if (!CollectionUtils.isEmpty(this.getBurgerIngredients())) {
            this.getBurgerIngredients().remove(burgerIngredient);
            this.getBurgerIngredients().add(burgerIngredient);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Burger)) return false;
        Burger burger = (Burger) o;
        return Objects.equals(getId(), burger.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
