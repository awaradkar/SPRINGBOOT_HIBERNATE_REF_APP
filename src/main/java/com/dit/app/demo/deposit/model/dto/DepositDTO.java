package com.dit.app.demo.deposit.model.dto;

import com.dit.app.demo.config.ZoneDateDeSerializer;
import com.dit.app.demo.config.ZoneDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class DepositDTO {

    private String depositId;

    private String txnId;

    private String clientId;

    private String clientName;

    private String warehouseCode;

    private String warehouseName;

    private String commodityCode;

    private String commodityName;

    private String godownCode;

    private String godownName;

    private String uomCode;

    private ZonedDateTime depositDate;

    private BigDecimal noOfBags;

    private BigDecimal quantity;

    private BigDecimal netQuantity;

    private String packType;

    private BigDecimal currenyQty;

    private BigDecimal currentPacks;

    private String active;

    private String createdBy;

    private ZonedDateTime createdDate;

    private String modifiedBy;

    private ZonedDateTime modifiedDate;

    private String status;

    public String getDepositId() {
        return depositId;
    }

    public void setDepositId(String depositId) {
        this.depositId = depositId;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getGodownCode() {
        return godownCode;
    }

    public void setGodownCode(String godownCode) {
        this.godownCode = godownCode;
    }

    public String getGodownName() {
        return godownName;
    }

    public void setGodownName(String godownName) {
        this.godownName = godownName;
    }

    public String getUomCode() {
        return uomCode;
    }

    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    @JsonSerialize(using = ZoneDateSerializer.class)
    @JsonDeserialize(using = ZoneDateDeSerializer.class)
    public ZonedDateTime getDepositDate() {
        return depositDate;
    }

    @JsonSerialize(using = ZoneDateSerializer.class)
    @JsonDeserialize(using = ZoneDateDeSerializer.class)
     public void setDepositDate(ZonedDateTime depositDate) {
        this.depositDate = depositDate;
    }

    public BigDecimal getNoOfBags() {
        return noOfBags;
    }

    public void setNoOfBags(BigDecimal noOfBags) {
        this.noOfBags = noOfBags;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getNetQuantity() {
        return netQuantity;
    }

    public void setNetQuantity(BigDecimal netQuantity) {
        this.netQuantity = netQuantity;
    }

    public String getPackType() {
        return packType;
    }

    public void setPackType(String packType) {
        this.packType = packType;
    }

    public BigDecimal getCurrenyQty() {
        return currenyQty;
    }

    public void setCurrenyQty(BigDecimal currenyQty) {
        this.currenyQty = currenyQty;
    }

    public BigDecimal getCurrentPacks() {
        return currentPacks;
    }

    public void setCurrentPacks(BigDecimal currentPacks) {
        this.currentPacks = currentPacks;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
