package com.dit.app.demo.deposit.model;

import com.dit.app.demo.config.ZonedateTimeConverter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "QUANTITY_LEDGER")
public class QuantityLedger {

    @EmbeddedId
    private QuantityLedgerId qtyId;

    @Column(scale = 5, precision = 25)
    private BigDecimal totalQuantity;

    @Column(scale = 5, precision = 25)
    private BigDecimal totalPacks;

    @Column(length = 20)
    private String createdBy;

    @Convert(converter = ZonedateTimeConverter.class)
    private ZonedDateTime createdDate;

    @Column(length = 20)
    private String modifiedBy;

    @Convert(converter = ZonedateTimeConverter.class)
    private ZonedDateTime modifiedDate;

    public QuantityLedgerId getQtyId() {
        return qtyId;
    }

    public void setQtyId(QuantityLedgerId qtyId) {
        this.qtyId = qtyId;
    }

    public BigDecimal getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(BigDecimal totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getTotalPacks() {
        return totalPacks;
    }

    public void setTotalPacks(BigDecimal totalPacks) {
        this.totalPacks = totalPacks;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public ZonedDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(ZonedDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
