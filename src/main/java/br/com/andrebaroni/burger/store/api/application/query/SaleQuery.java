package br.com.andrebaroni.burger.store.api.application.query;

import br.com.andrebaroni.burger.store.api.domain.entity.SaleStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class SaleQuery implements Serializable {

    @JsonProperty("idSale")
    private UUID idSale;

    @JsonProperty("saleDate")
    private LocalDateTime saleDate;

    @JsonProperty("finishDate")
    private LocalDateTime finishDate;

    @JsonProperty("cancelDate")
    private LocalDateTime cancelDate;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("saleStatus")
    private SaleStatus saleStatus;

    private SaleQuery() {
        super();
    }

    public SaleQuery(UUID idSale, LocalDateTime saleDate, LocalDateTime finishDate, LocalDateTime cancelDate, SaleStatus saleStatus, Double price) {
        this();
        this.setIdSale(idSale);
        this.setSaleDate(saleDate);
        this.setFinishDate(finishDate);
        this.setCancelDate(cancelDate);
        this.setSaleStatus(saleStatus);
        this.setPrice(price);
    }

    public UUID getIdSale() {
        return idSale;
    }

    public void setIdSale(UUID idSale) {
        this.idSale = idSale;
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

    public SaleStatus getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(SaleStatus saleStatus) {
        this.saleStatus = saleStatus;
    }
}
