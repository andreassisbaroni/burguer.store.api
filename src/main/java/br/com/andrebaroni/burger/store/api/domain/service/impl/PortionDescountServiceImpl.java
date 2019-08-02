package br.com.andrebaroni.burger.store.api.domain.service.impl;

import br.com.andrebaroni.burger.store.api.domain.entity.PortionDiscount;
import br.com.andrebaroni.burger.store.api.domain.service.PortionDiscountService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class PortionDescountServiceImpl implements PortionDiscountService {

    @Override
    public Collection<PortionDiscount> findAllActive() {
        return new ArrayList<>();
    }
}
