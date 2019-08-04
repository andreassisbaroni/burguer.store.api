package br.com.andrebaroni.burger.store.api.domain.service.impl;

import br.com.andrebaroni.burger.store.api.domain.entity.Discount;
import br.com.andrebaroni.burger.store.api.domain.service.DiscountService;
import br.com.andrebaroni.burger.store.api.infra.repository.IncludeExcludeDiscountRepository;
import br.com.andrebaroni.burger.store.api.infra.repository.PortionDiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final IncludeExcludeDiscountRepository includeExcludeDiscountRepository;
    private final PortionDiscountRepository portionDiscountRepository;

    @Autowired
    public DiscountServiceImpl(IncludeExcludeDiscountRepository includeExcludeDiscountRepository,
                               PortionDiscountRepository portionDiscountRepository) {
        this.includeExcludeDiscountRepository = includeExcludeDiscountRepository;
        this.portionDiscountRepository = portionDiscountRepository;
    }

    @Override
    public Collection<Discount> findAllActive() {
        Collection<Discount> discounts = new ArrayList<>();

        discounts.addAll(this.portionDiscountRepository.findByActiveOrderById(Boolean.TRUE));
        discounts.addAll(this.includeExcludeDiscountRepository.findByActiveOrderById(Boolean.TRUE));

        return discounts;
    }
}
