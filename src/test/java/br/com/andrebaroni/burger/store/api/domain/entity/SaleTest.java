package br.com.andrebaroni.burger.store.api.domain.entity;

import br.com.andrebaroni.burger.store.api.domain.exception.SaleAlreadyCanceledException;
import br.com.andrebaroni.burger.store.api.domain.exception.SaleAlreadyConcludedException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleTest {

    @Test
    public void addSaleItem() {
        Burger burger = new Burger("X-Burger");
        Sale sale = new Sale();
        SaleItem saleItem = new SaleItem(sale, burger);
        sale.addSaleItem(saleItem);

        Assertions.assertThat(sale.getSaleItems().size()).isEqualTo(1);
    }

    @Test
    public void addBurger() {
        Burger burger = new Burger("X-Burger");
        Sale sale = new Sale();
        sale.addBurger(burger);

        Assertions.assertThat(sale.getSaleItems().size()).isEqualTo(1);
    }

    @Test
    public void calculatePrice() {
        Ingredient meat = new Ingredient("Meat", 3.00);
        meat.setId(UUID.randomUUID());
        Ingredient cheese = new Ingredient("Cheese", 1.50);
        cheese.setId(UUID.randomUUID());

        Burger burger = new Burger("X-Burger");
        burger.addIngredient(meat, 2);
        burger.addIngredient(cheese, 1);

        Sale sale = new Sale();
        sale.addBurger(burger);

        Assertions.assertThat(sale.getPrice()).isEqualTo(7.50);
    }

    @Test
    public void getStatus() {
        Sale sale = new Sale();

        Assertions.assertThat(sale.getStatus()).isEqualTo(SaleStatus.PENDING);
    }

    @Test
    public void finish() {
        Sale sale = new Sale();
        sale.finish();

        Assertions.assertThat(sale.getStatus()).isEqualTo(SaleStatus.CONPLETED);
        Assertions.assertThat(sale.getFinishDate()).isNotNull();
    }

    @Test(expected = SaleAlreadyConcludedException.class)
    public void finishASaleAlreadyConcluded() {
        Sale sale = new Sale();
        sale.finish();
        sale.finish();
    }

    @Test(expected = SaleAlreadyCanceledException.class)
    public void finishASaleAlreadyCanceled() {
        Sale sale = new Sale();
        sale.cancel();
        sale.finish();
    }

    @Test
    public void cancel() {
        Sale sale = new Sale();
        sale.cancel();

        Assertions.assertThat(sale.getStatus()).isEqualTo(SaleStatus.CANCELED);
        Assertions.assertThat(sale.getCancelDate()).isNotNull();
    }

    @Test(expected = SaleAlreadyCanceledException.class)
    public void cancelASaleAreadyCanceled() {
        Sale sale = new Sale();
        sale.cancel();
        sale.cancel();
    }

    @Test(expected = SaleAlreadyConcludedException.class)
    public void cancelASaleAreadyConcluded() {
        Sale sale = new Sale();
        sale.finish();
        sale.cancel();
    }
}