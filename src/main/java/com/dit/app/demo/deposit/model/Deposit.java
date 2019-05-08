package com.dit.app.demo.deposit.model;

import com.dit.app.demo.config.ZonedateTimeConverter;
import com.dit.app.demo.model.BaseModel;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "DEPOSIT_DETAILS")
public class Deposit extends BaseModel implements Serializable {

    @Id
    @Column(length = 10)
    private String depositId;

    @Column(length = 10)
    private String txnId;

    @Column(length = 20)
    private String clientId;

    @Column(length = 100)
    private String clientName;

    @Column(length = 20)
    private String warehouseCode;

    @Column(length = 100)
    private String warehouseName;

    @Column(length = 20)
    private String commodityCode;

    @Column(length = 100)
    private String commodityName;

    @Column(length = 10)
    private String godownCode;

    @Column(length = 100)
    private String godownName;

    @Column(length = 10)
    private String uomCode;

    @Convert(converter = ZonedateTimeConverter.class)
    private ZonedDateTime depositDate;

    @Column(scale = 5, precision = 25)
    private BigDecimal noOfBags;

    @Column(scale = 5, precision = 25)
    private BigDecimal quantity;

    @Column(scale = 5, precision = 25)
    private BigDecimal netQuantity;

    @Column(length = 20)
    private String packType;

    @Column(scale = 5, precision = 25)
    private BigDecimal currenyQty;

    @Column(scale = 5, precision = 25)
    private BigDecimal currentPacks;

    @Column
    private String active;

    @Column(length = 20)
    private String createdBy;

    @Convert(converter = ZonedateTimeConverter.class)
    private ZonedDateTime createdDate;

    @Column(length = 20)
    private String modifiedBy;

    @Convert(converter = ZonedateTimeConverter.class)
    private ZonedDateTime modifiedDate;

    @Column(length = 20)
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

    public ZonedDateTime getDepositDate() {
        return depositDate;
    }

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