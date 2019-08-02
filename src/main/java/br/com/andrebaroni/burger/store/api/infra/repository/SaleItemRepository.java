package br.com.andrebaroni.burger.store.api.infra.repository;

import br.com.andrebaroni.burger.store.api.domain.entity.Sale;
import br.com.andrebaroni.burger.store.api.domain.entity.SaleItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.UUID;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, UUID>, Serializable {

    Page<SaleItem> findBySaleOrderByIdDesc(Sale sale, Pageable pageable);
}
