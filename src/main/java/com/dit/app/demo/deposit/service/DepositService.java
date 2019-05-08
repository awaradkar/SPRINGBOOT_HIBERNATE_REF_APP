package com.dit.app.demo.deposit.service;

import com.dit.app.demo.deposit.model.dto.DepositDTO;

import java.time.ZonedDateTime;
import java.util.List;

public interface DepositService {
    List<DepositDTO> getDepositList(String warehouseCode, String warehouseName, String clientId, String clientName, String commodityCode,
                                    String commodityName, ZonedDateTime startDate, ZonedDateTime endDate, String status);

    DepositDTO saveDeposit(DepositDTO deposit) throws Exception;

    DepositDTO updateDeposit(DepositDTO deposit) throws Exception;

    void deleteDeposit(String id);

    DepositDTO getDeposit(String id);

    Long getCommDeposits(String commodityCode);

    Long getOrgDeposits(String orgCode);
}
