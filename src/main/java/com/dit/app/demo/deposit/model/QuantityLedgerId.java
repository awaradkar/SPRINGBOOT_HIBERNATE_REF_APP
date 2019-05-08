package com.dit.app.demo.deposit.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class QuantityLedgerId implements Serializable {

    @Column(length = 20)
    private String warehouseCode;

    @Column(length = 20)
    private String clientId;

    @Column(length = 20)
    private String commodityCode;

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuantityLedgerId that = (QuantityLedgerId) o;

        if (!warehouseCode.equals(that.warehouseCode)) return false;
        else if (!clientId.equals(that.clientId)) return false;
        return commodityCode.equals(that.commodityCode);
    }

    @Override
    public int hashCode() {
        int result = 31 * warehouseCode.hashCode() + clientId.hashCode() + commodityCode.hashCode();
        return result;
    }
}
