package br.com.andrebaroni.burger.store.api.domain.service;

import br.com.andrebaroni.burger.store.api.domain.entity.PortionDiscount;

import java.io.Serializable;
import java.util.Collection;

public interface PortionDiscountService extends Serializable {

    Collection<PortionDiscount> findAllActive();
}
