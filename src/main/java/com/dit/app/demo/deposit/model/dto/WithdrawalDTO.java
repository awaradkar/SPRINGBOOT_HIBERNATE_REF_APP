package com.dit.app.demo.deposit.model.dto;

import com.dit.app.demo.config.ZoneDateDeSerializer;
import com.dit.app.demo.config.ZoneDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class WithdrawalDTO {

    private String withdrawalId;

    private String depositId;

    private String txnId;

    private String clientId;

    private String clientName;

    private String warehouseCode;

    private String warehouseName;

    private String commodityCode;

    private String commodityName;

    private String uomCode;

    private ZonedDateTime withdrawalDate;

    private BigDecimal withDrawalNoOfBags;

    private BigDecimal withdrawalQuantity;

    private BigDecimal netQuantity;

    private String packType;

    private String createdBy;

    private ZonedDateTime createdDate;

    private String modifiedBy;

    private ZonedDateTime modifiedDate;

    private String active;

    private String status;

    public String getWithdrawalId() {
        return withdrawalId;
    }

    public void setWithdrawalId(String withdrawalId) {
        this.withdrawalId = withdrawalId;
    }

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

    public String getUomCode() {
        return uomCode;
    }

    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    @JsonSerialize(using = ZoneDateSerializer.class)
    @JsonDeserialize(using = ZoneDateDeSerializer.class)
    public ZonedDateTime getWithdrawalDate() {
        return withdrawalDate;
    }

    @JsonSerialize(using = ZoneDateSerializer.class)
    @JsonDeserialize(using = ZoneDateDeSerializer.class)
    public void setWithdrawalDate(ZonedDateTime withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }

    public BigDecimal getWithDrawalNoOfBags() {
        return withDrawalNoOfBags;
    }

    public void setWithDrawalNoOfBags(BigDecimal withDrawalNoOfBags) {
        this.withDrawalNoOfBags = withDrawalNoOfBags;
    }

    public BigDecimal getWithdrawalQuantity() {
        return withdrawalQuantity;
    }

    public void setWithdrawalQuantity(BigDecimal withdrawalQuantity) {
        this.withdrawalQuantity = withdrawalQuantity;
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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
