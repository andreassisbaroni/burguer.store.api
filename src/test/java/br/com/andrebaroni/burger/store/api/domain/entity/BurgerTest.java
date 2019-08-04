package br.com.andrebaroni.burger.store.api.domain.entity;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BurgerTest {

    @Test
    public void addIngredientWithBurgerIngredient() {
        Ingredient meat = new Ingredient("Meat", 3.00);
        meat.setId(UUID.randomUUID());
        Burger burger = new Burger("X-Burger");
        BurgerIngredient burgerIngredient = new BurgerIngredient(burger, meat, 1);
        burger.addIngredient(burgerIngredient);

        Assertions.assertThat(burger.getBurgerIngredients().size()).isEqualTo(1);
    }

    @Test
    public void incrementAmountOfIngredientWithBurgerIngredient() {
        Ingredient meat = new Ingredient("Meat", 3.00);
        meat.setId(UUID.randomUUID());
        Burger burger = new Burger("X-Burger");
        BurgerIngredient firstIngredient = new BurgerIngredient(burger, meat, 1);
        burger.addIngredient(firstIngredient);

        Assertions.assertThat(burger.getBurgerIngredients().size()).isEqualTo(1);
        Assertions.assertThat(
                burger.getBurgerIngredients()
                        .parallelStream()
                        .mapToInt(BurgerIngredient::getAmount).sum()
        ).isEqualTo(1);

        BurgerIngredient secondIngredient = new BurgerIngredient(burger, meat, 2);
        burger.addIngredient(secondIngredient);

        Assertions.assertThat(burger.getBurgerIngredients().size()).isEqualTo(1);
        Assertions.assertThat(
                burger.getBurgerIngredients()
                        .parallelStream()
                        .mapToInt(BurgerIngredient::getAmount).sum()
        ).isEqualTo(3);
    }

    @Test
    public void shouldAddIngredientWithIngredient() {
        Ingredient meat = new Ingredient("Meat", 3.00);
        Burger burger = new Burger("X-Burger");
        burger.addIngredient(meat, 2);

        Assertions.assertThat(burger.getBurgerIngredients().size()).isEqualTo(1);
    }


    @Test
    public void incrementAmountOfIngredientWithIngredient() {
        Ingredient meat = new Ingredient("Meat", 3.00);
        meat.setId(UUID.randomUUID());
        Burger burger = new Burger("X-Burger");
        burger.addIngredient(meat, 2);

        Assertions.assertThat(burger.getBurgerIngredients().size()).isEqualTo(1);
        Assertions.assertThat(
                burger.getBurgerIngredients()
                        .parallelStream()
                        .mapToInt(BurgerIngredient::getAmount).sum()
        ).isEqualTo(2);

        burger.addIngredient(meat, 5);

        Assertions.assertThat(burger.getBurgerIngredients().size()).isEqualTo(1);
        Assertions.assertThat(
                burger.getBurgerIngredients()
                        .parallelStream()
                        .mapToInt(BurgerIngredient::getAmount).sum()
        ).isEqualTo(7);
    }

    @Test
    public void shouldCalculatePrice() {
        Ingredient meat = new Ingredient("Meat", 3.00);
        meat.setId(UUID.randomUUID());
        Ingredient cheese = new Ingredient("Chese", 1.50);
        cheese.setId(UUID.randomUUID());
        Burger burger = new Burger("X-Burger");
        burger.addIngredient(meat, 2);
        burger.addIngredient(cheese, 2);

        Assertions.assertThat(burger.getBurgerIngredients().size()).isEqualTo(2);
    }

    @Test
    public void applyDiscountWithDiscountNull() {
        Ingredient meat = new Ingredient("Meat", 3.00);
        meat.setId(UUID.randomUUID());
        Ingredient cheese = new Ingredient("Cheese", 1.50);
        cheese.setId(UUID.randomUUID());
        Burger burger = new Burger("X-Burger");
        burger.addIngredient(meat, 3);
        burger.addIngredient(cheese, 1);

        burger.applyDiscount(null);

        Assertions.assertThat(burger.getPrice()).isEqualTo(10.50);
    }

    @Test
    public void applyDiscountWithDiscountNotActive() {
        Ingredient meat = new Ingredient("Meat", 3.00);
        meat.setId(UUID.randomUUID());
        Ingredient cheese = new Ingredient("Cheese", 1.50);
        cheese.setId(UUID.randomUUID());

        Burger burger = new Burger("X-Burger");

        burger.addIngredient(meat, 3);
        burger.addIngredient(cheese, 1);

        burger.applyDiscount(new PortionDiscount("A lot of Meat", meat, 3, 1));

        Assertions.assertThat(burger.getPrice()).isEqualTo(10.50);
    }

    @Test
    public void applyPortionDiscount() {
        Ingredient meat = new Ingredient("Meat", 3.00);
        meat.setId(UUID.randomUUID());
        Ingredient cheese = new Ingredient("Cheese", 1.50);
        cheese.setId(UUID.randomUUID());

        Burger burger = new Burger("X-Burger");

        burger.addIngredient(meat, 3);
        burger.addIngredient(cheese, 1);

        burger.applyDiscount(new PortionDiscount("A lot of Meat", true, meat, 3, 1));

        Assertions.assertThat(burger.getPrice()).isEqualTo(7.50);
    }

    @Test
    public void applyPortionDiscountTwoTimes() {
        Ingredient meat = new Ingredient("Meat", 3.00);
        meat.setId(UUID.randomUUID());
        Ingredient cheese = new Ingredient("Cheese", 1.50);
        cheese.setId(UUID.randomUUID());

        Burger burger = new Burger("X-Burger");

        burger.addIngredient(meat, 6);
        burger.addIngredient(cheese, 1);

        burger.applyDiscount(new PortionDiscount("A lot of Meat", true, meat, 3, 1));

        Assertions.assertThat(burger.getPrice()).isEqualTo(13.50);
    }

    @Test
    public void applyTwoDifferentPortionDiscount() {
        Ingredient meat = new Ingredient("Meat", 3.00);
        meat.setId(UUID.randomUUID());
        Ingredient cheese = new Ingredient("Cheese", 1.50);
        cheese.setId(UUID.randomUUID());

        Burger burger = new Burger("X-Burger");

        burger.addIngredient(meat, 6);
        burger.addIngredient(cheese, 3);

        burger.applyDiscount(new PortionDiscount("A lot of Meat", true, meat, 3, 1));
        burger.applyDiscount(new PortionDiscount("A lot of Cheese", true, cheese, 3, 1));

        Assertions.assertThat(burger.getPrice()).isEqualTo(15.00);
    }

    @Test
    public void notApplyPortionDiscountBecauseAmountRequested() {
        Ingredient meat = new Ingredient("Meat", 3.00);
        meat.setId(UUID.randomUUID());
        Ingredient cheese = new Ingredient("Cheese", 1.50);
        cheese.setId(UUID.randomUUID());

        Burger burger = new Burger("X-Burger");

        burger.addIngredient(meat, 1);
        burger.addIngredient(cheese, 1);

        burger.applyDiscount(new PortionDiscount("A lot of Meat", true, meat, 3, 1));

        Assertions.assertThat(burger.getPrice()).isEqualTo(4.50);
    }

    @Test
    public void applyIncludeExcludeDiscount() {
        Ingredient meat = new Ingredient("Meat", 3.00);
        meat.setId(UUID.randomUUID());
        Ingredient cheese = new Ingredient("Cheese", 1.50);
        Ingredient bacon = new Ingredient("Bacon", 2.0);
        bacon.setId(UUID.randomUUID());

        Burger burger = new Burger("X-Burger");

        burger.addIngredient(meat, 2);
        burger.addIngredient(cheese, 1);

        burger.applyDiscount(new IncludeExcludeDiscount("Meat without Bacon", true, meat, bacon, 10.0));

        Assertions.assertThat(burger.getPrice()).isEqualTo(6.75);
    }

    @Test
    public void notApplyIncludeExcludeDiscountBecauseIsNotActive() {
        Ingredient meat = new Ingredient("Meat", 3.00);
        meat.setId(UUID.randomUUID());
        Ingredient cheese = new Ingredient("Cheese", 1.50);
        Ingredient bacon = new Ingredient("Bacon", 2.0);
        bacon.setId(UUID.randomUUID());

        Burger burger = new Burger("X-Burger");

        burger.addIngredient(meat, 2);
        burger.addIngredient(cheese, 1);

        burger.applyDiscount(new IncludeExcludeDiscount("Meat without Bacon", meat, bacon, 10.0));

        Assertions.assertThat(burger.getPrice()).isEqualTo(7.50);
    }

    @Test
    public void notApplyIncludeExcludeDiscountBecauseHasExcludedIngredient() {
        Ingredient meat = new Ingredient("Meat", 3.00);
        meat.setId(UUID.randomUUID());
        Ingredient cheese = new Ingredient("Cheese", 1.50);
        cheese.setId(UUID.randomUUID());
        Ingredient bacon = new Ingredient("Bacon", 2.0);
        bacon.setId(UUID.randomUUID());

        Burger burger = new Burger("X-Burger");

        burger.addIngredient(meat, 2);
        burger.addIngredient(cheese, 1);
        burger.addIngredient(bacon, 1);

        burger.applyDiscount(new IncludeExcludeDiscount("Meat without Bacon", true, meat, bacon, 10.0));

        Assertions.assertThat(burger.getPrice()).isEqualTo(9.50);
    }

    @Test
    public void applyIncludeExcludeDiscountAndPortionDiscount() {
        Ingredient meat = new Ingredient("Meat", 3.00);
        meat.setId(UUID.randomUUID());
        Ingredient cheese = new Ingredient("Cheese", 1.50);
        cheese.setId(UUID.randomUUID());
        Ingredient bacon = new Ingredient("Bacon", 2.0);
        bacon.setId(UUID.randomUUID());

        Burger burger = new Burger("X-Burger");

        burger.addIngredient(meat, 3);
        burger.addIngredient(cheese, 1);

        burger.applyDiscount(new PortionDiscount("A lot of Meat", true, meat, 3, 1));
        burger.applyDiscount(new IncludeExcludeDiscount("Meat without Bacon", true, meat, bacon, 10.0));

        Assertions.assertThat(burger.getPrice()).isEqualTo(6.75);
    }
}