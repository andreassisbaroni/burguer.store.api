package br.com.andrebaroni.burger.store.api.domain.service;

import br.com.andrebaroni.burger.store.api.domain.entity.IncludeExcludeDiscount;

import java.io.Serializable;
import java.util.Collection;

public interface IncludeExcludeDiscountService extends Serializable {

    Collection<IncludeExcludeDiscount> findAllActive();
}
