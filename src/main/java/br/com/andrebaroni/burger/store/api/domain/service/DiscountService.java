package br.com.andrebaroni.burger.store.api.domain.service;

import br.com.andrebaroni.burger.store.api.domain.entity.Discount;

import java.io.Serializable;
import java.util.Collection;

public interface DiscountService extends Serializable {

    Collection<Discount> findAllActive();
}
