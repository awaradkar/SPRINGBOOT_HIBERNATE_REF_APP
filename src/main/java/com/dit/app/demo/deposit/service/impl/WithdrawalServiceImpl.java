package com.dit.app.demo.deposit.service.impl;

import com.dit.app.demo.dao.IdGeneratorDao;
import com.dit.app.demo.deposit.dao.WithdrawalDao;
import com.dit.app.demo.deposit.model.Withdrawal;
import com.dit.app.demo.deposit.model.dto.DepositDTO;
import com.dit.app.demo.deposit.model.dto.WithdrawalDTO;
import com.dit.app.demo.deposit.service.DepositService;
import com.dit.app.demo.deposit.service.WithdrawalService;
import com.dit.app.demo.exception.EntityNotFoundException;
import com.dit.app.demo.helper.EntityConversion;
import com.dit.app.demo.model.IdGenerator;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WithdrawalServiceImpl implements WithdrawalService {

    @Autowired
    WithdrawalDao withDrawDao;

    @Autowired
    DepositService depositService;

    @Autowired
    IdGeneratorDao idGeneratorDao;

    @Override
    public List<WithdrawalDTO> getWithdrawalList(String withdrawalId, String depositId, String warehouseCode, String warehouseName, String clientId, String clientName, String commodityCode, String commodityName, ZonedDateTime startDate, ZonedDateTime endDate) {
        List<WithdrawalDTO> withdrawalDTOList = new ArrayList<>();
        List<Withdrawal> withdrawalList = new ArrayList<>();

        withdrawalList = withDrawDao.getWithDrawalList(withdrawalId, depositId, warehouseCode, warehouseName, clientId, clientName, commodityCode, commodityName, startDate, endDate);

        for (Withdrawal withdrawal : withdrawalList) {
            WithdrawalDTO dto = new WithdrawalDTO();
            dto = (WithdrawalDTO) EntityConversion.convertModel(withdrawal, dto, EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
            withdrawalDTOList.add(dto);
        }

        return withdrawalDTOList;
    }

    @Override
    public WithdrawalDTO getWithdrawalDtls(String id) {
        Withdrawal withdrawal = withDrawDao.getWithdrawal(id);
        WithdrawalDTO dto = null;
        if (withdrawal != null) {
            dto = new WithdrawalDTO();
            dto = (WithdrawalDTO) EntityConversion.convertModel(withdrawal, dto, EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
        }
        return dto;
    }

    @Override
    public WithdrawalDTO saveWithdrawal(WithdrawalDTO withdrawalDTO) throws Exception {
        Withdrawal withdrawal = new Withdrawal();
        withdrawal = (Withdrawal) EntityConversion.convertModel(withdrawal, withdrawalDTO, EntityConversion.ConversionEnum.DTOTOENTITY.ordinal());
        withdrawal.setCreatedDate(ZonedDateTime.now());
        try {

            updateAndSaveDeposit(withdrawalDTO);
            IdGenerator idGenerator = idGeneratorDao.getNewId("TXN");
            IdGenerator idGeneratorWith = idGeneratorDao.getNewId("WDH");

            withdrawal.setTxnId(idGenerator.getIdKey()+""+idGenerator.getIdValue());
            withdrawal.setWithdrawalId(idGeneratorWith.getIdKey()+""+idGeneratorWith.getIdValue());

            withdrawal = withDrawDao.saveWithDrawal(withdrawal);

                withdrawalDTO = (WithdrawalDTO) EntityConversion.convertModel(withdrawal, withdrawalDTO, EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
        } catch (HibernateException e) {
            throw new Exception(e.getMessage());
        }
        return withdrawalDTO;
    }

    @Override
    public WithdrawalDTO updateWithdrawal(WithdrawalDTO withdrawalDTO) throws Exception {
         try {
             Withdrawal withdrawal = new Withdrawal();

             if("CANCELLED".equals((withdrawalDTO.getStatus()))){
                 withdrawalDTO.setActive("N");
                 updateAndSaveDepositAfterCancellation(withdrawalDTO);
             }

             withdrawal = (Withdrawal) EntityConversion.convertModel(withdrawal, withdrawalDTO, EntityConversion.ConversionEnum.DTOTOENTITY.ordinal());
             withdrawal.setModifiedDate(ZonedDateTime.now());
             withdrawal = withDrawDao.updateWithDrawal(withdrawal);
             withdrawalDTO = (WithdrawalDTO) EntityConversion.convertModel(withdrawal, withdrawalDTO,
                     EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
        } catch (HibernateException e) {
            throw new Exception(e.getMessage());
        }
        return withdrawalDTO;
    }

    @Override
    public void deleteWithdrawal(String id) {
        try {
            withDrawDao.deleteWithdrawal(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(id);
        }
    }

    private void updateAndSaveDeposit(WithdrawalDTO withdrawalDTO) {
        DepositDTO depositDTO = depositService.getDeposit(withdrawalDTO.getDepositId());
        BigDecimal currQty = depositDTO.getCurrenyQty();
        BigDecimal currPacks = depositDTO.getCurrentPacks();
        depositDTO.setCurrentPacks(currPacks.subtract(withdrawalDTO.getWithDrawalNoOfBags()));
        depositDTO.setCurrenyQty(currQty.subtract(withdrawalDTO.getWithdrawalQuantity()));

        if(BigDecimal.ZERO.compareTo(depositDTO.getCurrenyQty())==0 || BigDecimal.ZERO.compareTo(depositDTO.getCurrentPacks())==0)
            depositDTO.setActive("N");
        try {
            depositService.updateDeposit(depositDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateAndSaveDepositAfterCancellation(WithdrawalDTO withdrawalDTO) {
        DepositDTO depositDTO = depositService.getDeposit(withdrawalDTO.getDepositId());
        BigDecimal currQty = depositDTO.getCurrenyQty();
        BigDecimal currPacks = depositDTO.getCurrentPacks();
        depositDTO.setCurrentPacks(currPacks.add(withdrawalDTO.getWithDrawalNoOfBags()));
        depositDTO.setCurrenyQty(currQty.add(withdrawalDTO.getWithdrawalQuantity()));

        if("N".equals(depositDTO.getActive()))
            depositDTO.setActive("Y");
        try {
            depositService.updateDeposit(depositDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
