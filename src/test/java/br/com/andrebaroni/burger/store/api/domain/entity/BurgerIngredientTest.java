package br.com.andrebaroni.burger.store.api.domain.entity;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BurgerIngredientTest {

    @Test
    public void shouldCalculatePriceWithoutDiscounts() {
        Ingredient meat = new Ingredient("Meat", 3.00);
        Burger burger = new Burger("X-Burger");
        BurgerIngredient burgerMeat = new BurgerIngredient(burger, meat, 4);

        Assertions.assertThat(burgerMeat.getPrice()).isEqualTo(12.00);
    }

    @Test
    public void shouldCalculatePriceWithPortionDiscount() {
        Ingredient meat = new Ingredient("Meat", 3.00);
        Burger burger = new Burger("X-Burger");
        BurgerIngredient burgerMeat = new BurgerIngredient(burger, meat, 5);
        burgerMeat.setAmountDiscount(1);

        Assertions.assertThat(burgerMeat.getPrice()).isEqualTo(12.00);
    }
}