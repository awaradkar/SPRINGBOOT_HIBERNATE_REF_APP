package com.dit.app.demo.deposit.service;

import com.dit.app.demo.deposit.model.dto.WithdrawalDTO;

import java.time.ZonedDateTime;
import java.util.List;

public interface WithdrawalService {
    List<WithdrawalDTO> getWithdrawalList(String withdrawalId, String depositId, String warehouseCode, String warehouseName, String clientId, String clientName, String commodityCode, String commodityName, ZonedDateTime startDate, ZonedDateTime endDate);

    WithdrawalDTO getWithdrawalDtls(String id);

    WithdrawalDTO saveWithdrawal(WithdrawalDTO withdrawalDTO) throws Exception;

    WithdrawalDTO updateWithdrawal(WithdrawalDTO withdrawalDTO) throws Exception;

    void deleteWithdrawal(String id);
}
