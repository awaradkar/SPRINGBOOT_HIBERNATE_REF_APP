package com.dit.app.demo.deposit.service;

import com.dit.app.demo.deposit.model.dto.QuantityLedgerDTO;

import java.time.ZonedDateTime;
import java.util.List;

public interface QuantityLedgerService {
    List<QuantityLedgerDTO> getQuantityLedgerList(String warehouseCode, String warehouseName, String clientId, String clientName, String commodityCode, String commodityName, ZonedDateTime startDate, ZonedDateTime endDate);
}
