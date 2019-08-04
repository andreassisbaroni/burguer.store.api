package br.com.andrebaroni.burger.store.api.domain.entity;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleItemTest {

    @Test
    public void shouldCalculatePrice() {
        Ingredient meat = new Ingredient("Meat", 3.00);
        meat.setId(UUID.randomUUID());
        Ingredient cheese = new Ingredient("Cheese", 1.50);
        meat.setId(UUID.randomUUID());

        Burger burger = new Burger("X-Burger");

        burger.addIngredient(meat, 2);
        burger.addIngredient(cheese, 2);

        Sale sale = new Sale();
        SaleItem itemBurger = new SaleItem(sale, burger);

        Assertions.assertThat(itemBurger.getPrice()).isEqualTo(9.00);
    }

    @Test
    public void shouldGetBurgerDescriptionWithoutBurger() {
        SaleItem saleItem = new SaleItem(null, null);

        Assertions.assertThat(saleItem.getBurgerDescription()).isEqualTo(null);
    }

    @Test
    public void shouldGetBurgerDescriptionWithBurger() {
        Burger burger = new Burger("X-Burger");
        SaleItem saleItem = new SaleItem(null, burger);

        Assertions.assertThat(saleItem.getBurgerDescription()).isEqualTo("X-Burger");
    }

    @Test
    public void shouldGetBurgerIdWithoutBurger() {
        SaleItem saleItem = new SaleItem(null, null);

        Assertions.assertThat(saleItem.getIdBurger()).isEqualTo(null);
    }

    @Test
    public void shouldGetBurgerIdWithBurger() {
        Burger burger = new Burger("X-Burger");
        burger.setId(UUID.fromString("66a0e02b-da8e-4877-8c4e-365179b0b9ef"));
        SaleItem saleItem = new SaleItem(null, burger);

        Assertions.assertThat(saleItem.getIdBurger()).isEqualTo(UUID.fromString("66a0e02b-da8e-4877-8c4e-365179b0b9ef"));
    }

}