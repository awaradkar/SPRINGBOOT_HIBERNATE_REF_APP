package com.dit.app.demo.deposit.dao;

import com.dit.app.demo.deposit.model.Withdrawal;

import java.time.ZonedDateTime;
import java.util.List;

public interface WithdrawalDao {
    List<Withdrawal> getWithDrawalList(String withdrawalId, String depositId, String warehouseCode, String warehouseName, String clientId, String clientName, String commodityCode, String commodityName, ZonedDateTime startDate, ZonedDateTime endDate);

    Withdrawal getWithdrawal(String id);

    Withdrawal saveWithDrawal(Withdrawal withdrawal);

    Withdrawal updateWithDrawal(Withdrawal withdrawal);

    void deleteWithdrawal(String id);
}
