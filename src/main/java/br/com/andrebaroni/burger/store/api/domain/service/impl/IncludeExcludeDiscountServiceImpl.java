package br.com.andrebaroni.burger.store.api.domain.service.impl;

import br.com.andrebaroni.burger.store.api.domain.entity.IncludeExcludeDiscount;
import br.com.andrebaroni.burger.store.api.domain.service.IncludeExcludeDiscountService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class IncludeExcludeDiscountServiceImpl implements IncludeExcludeDiscountService {

    @Override
    public Collection<IncludeExcludeDiscount> findAllActive() {
        return new ArrayList<>();
    }
}
