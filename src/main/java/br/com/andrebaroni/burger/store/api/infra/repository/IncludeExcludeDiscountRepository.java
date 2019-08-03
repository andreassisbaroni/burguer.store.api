package br.com.andrebaroni.burger.store.api.infra.repository;

import br.com.andrebaroni.burger.store.api.domain.entity.IncludeExcludeDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Repository
public interface IncludeExcludeDiscountRepository extends JpaRepository<IncludeExcludeDiscount, UUID>, Serializable {

    Collection<IncludeExcludeDiscount> findByActiveOrderById(Boolean active);
}
