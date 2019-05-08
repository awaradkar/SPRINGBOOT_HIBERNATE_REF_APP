package com.dit.app.demo.deposit.model;

import com.dit.app.demo.config.ZonedateTimeConverter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "WITHDRAWAL_DETAILS")
public class Withdrawal {

    @Id
    @Column(length = 10)
    private String withdrawalId;

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
    private String uomCode;

    @Convert(converter = ZonedateTimeConverter.class)
    private ZonedDateTime withdrawalDate;

    @Column(scale = 5, precision = 25)
    private BigDecimal withDrawalNoOfBags;

    @Column(scale = 5, precision = 25)
    private BigDecimal withdrawalQuantity;

    @Column(scale = 5, precision = 25)
    private BigDecimal netQuantity;

    @Column(length = 20)
    private String packType;

    @Column(length = 20)
    private String createdBy;

    @Convert(converter = ZonedateTimeConverter.class)
    private ZonedDateTime createdDate;

    @Column(length = 20)
    private String modifiedBy;

    @Convert(converter = ZonedateTimeConverter.class)
    private ZonedDateTime modifiedDate;

    @Column(length = 2)
    private String active;

    @Column(length = 20)
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

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
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

    public ZonedDateTime getWithdrawalDate() {
        return withdrawalDate;
    }

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
