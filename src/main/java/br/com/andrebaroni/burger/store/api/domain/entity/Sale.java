package br.com.andrebaroni.burger.store.api.domain.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table
public class Sale implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column
    private LocalDateTime saleDate;

    @Column
    private LocalDateTime finishDate;

    @Column
    private LocalDateTime cancelDate;

    @Column
    private Double price;

    @OneToMany(mappedBy = "sale")
    private Collection<SaleItem> saleItems;

    public Sale() {
        super();
        this.saleDate = LocalDateTime.now();
        this.setSaleItems(new ArrayList<>());
    }

    public Sale(Collection<SaleItem> saleItems) {
        this();
        this.setSaleItems(saleItems);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public LocalDateTime getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(LocalDateTime cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Collection<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(Collection<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public void addSaleItem(SaleItem saleItem) {
        if (Objects.isNull(this.getSaleItems())) {
            this.setSaleItems(new ArrayList<>());
        }

        this.getSaleItems().add(saleItem);
    }

    public void calculatePrice() {
        if (!CollectionUtils.isEmpty(this.getSaleItems())) {
            this.setPrice(this.getSaleItems().parallelStream().mapToDouble(SaleItem::getPrice).sum());
        }
    }

    public SaleStatus getStatus() {
        if (Objects.nonNull(this.getCancelDate())) {
            return SaleStatus.CANCELED;
        } else if (Objects.nonNull(this.getFinishDate())) {
            return SaleStatus.CONPLETED;
        } else {
            return SaleStatus.PENDING;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale)) return false;
        Sale sale = (Sale) o;
        return Objects.equals(getId(), sale.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
