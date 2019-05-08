package com.dit.app.demo.deposit.dao;

import com.dit.app.demo.deposit.model.QuantityLedger;

import java.time.ZonedDateTime;
import java.util.List;

public interface QuantityLedgerDao {
    List<QuantityLedger> getQuantityLedgerList(String warehouseCode, String warehouseName, String clientId, String clientName, String commodityCode, String commodityName, ZonedDateTime startDate, ZonedDateTime endDate);
}
