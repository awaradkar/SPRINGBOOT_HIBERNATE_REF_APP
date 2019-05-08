package com.dit.app.demo.deposit.model.dto;

import com.dit.app.demo.config.ZoneDateDeSerializer;
import com.dit.app.demo.config.ZoneDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class QuantityLedgerDTO {

    private String warehouseCode;

    private String commodityCode;

    private String clientId;

    private BigDecimal totalQuantity;

    private BigDecimal totalPacks;

    private String createdBy;

    private ZonedDateTime createdDate;

    private String modifiedBy;

    private ZonedDateTime modifiedDate;

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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

    @JsonSerialize(using = ZoneDateSerializer.class)
    @JsonDeserialize(using = ZoneDateDeSerializer.class)
    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    @JsonSerialize(using = ZoneDateSerializer.class)
    @JsonDeserialize(using = ZoneDateDeSerializer.class)
    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @JsonSerialize(using = ZoneDateSerializer.class)
    @JsonDeserialize(using = ZoneDateDeSerializer.class)
    public ZonedDateTime getModifiedDate() {
        return modifiedDate;
    }

    @JsonSerialize(using = ZoneDateSerializer.class)
    @JsonDeserialize(using = ZoneDateDeSerializer.class)
    public void setModifiedDate(ZonedDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
