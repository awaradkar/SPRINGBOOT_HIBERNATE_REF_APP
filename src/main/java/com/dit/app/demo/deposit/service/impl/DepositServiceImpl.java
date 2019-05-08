package com.dit.app.demo.deposit.service.impl;

import com.dit.app.demo.dao.IdGeneratorDao;
import com.dit.app.demo.deposit.dao.DepositDao;
import com.dit.app.demo.deposit.model.Deposit;
import com.dit.app.demo.deposit.model.dto.DepositDTO;
import com.dit.app.demo.deposit.service.DepositService;
import com.dit.app.demo.exception.EntityNotFoundException;
import com.dit.app.demo.helper.EntityConversion;
import com.dit.app.demo.model.IdGenerator;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service

public class DepositServiceImpl implements DepositService {

    @Autowired
    DepositDao depDao;

    @Autowired
    IdGeneratorDao idGeneratorDao;

    // @Autowired
    //  HibernateTransactionManager hibernateTransactionManager;

    @Override
    public List<DepositDTO> getDepositList(String warehouseCode, String warehouseName, String clientId, String clientName, String commodityCode, String commodityName,
                                           ZonedDateTime startDate, ZonedDateTime endDate, String status) {
        List<DepositDTO> depositDTOList = new ArrayList<>();
        List<Deposit> depositList = new ArrayList<>();

        depositList = depDao.getDepositList(warehouseCode, warehouseName, clientId, clientName, commodityCode, commodityName, startDate, endDate, status);

        for (Deposit deposit : depositList) {
            DepositDTO dto = new DepositDTO();
            dto = (DepositDTO) EntityConversion.convertModel(deposit, dto, EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
            depositDTOList.add(dto);
        }

        return depositDTOList;
    }

    @Override
    public DepositDTO getDeposit(String id) {
        Deposit deposit = depDao.getDeposit(id);
        DepositDTO dto = null;
        if(deposit!=null) {
            dto = new DepositDTO();
            dto = (DepositDTO) EntityConversion.convertModel(deposit, dto, EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
        }

        return dto;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public DepositDTO saveDeposit(DepositDTO depositDTO) throws Exception{
        Deposit deposit = new Deposit();
        deposit = (Deposit) EntityConversion.convertModel(deposit, depositDTO, EntityConversion.ConversionEnum.DTOTOENTITY.ordinal());
        deposit.setCreatedDate(ZonedDateTime.now());

        IdGenerator idGenerator = idGeneratorDao.getNewId("TXN");
        IdGenerator idGeneratorDep = idGeneratorDao.getNewId("DEP");

        deposit.setTxnId(idGenerator.getIdKey()+""+idGenerator.getIdValue());
        deposit.setDepositId(idGeneratorDep.getIdKey()+""+idGeneratorDep.getIdValue());
        try {
            deposit = depDao.saveDeposit(deposit);

            depositDTO = (DepositDTO) EntityConversion.convertModel(deposit, depositDTO, EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
        }
        catch (HibernateException e) {
            throw new Exception(e.getMessage());
        }

        return depositDTO;
    }

    @Override
    public DepositDTO updateDeposit(DepositDTO depositDTO) throws Exception {
        Deposit deposit = new Deposit();
        deposit = (Deposit) EntityConversion.convertModel(deposit, depositDTO, EntityConversion.ConversionEnum.DTOTOENTITY.ordinal());
        deposit.setModifiedDate(ZonedDateTime.now());
        try {
            deposit = depDao.updateDeposit(deposit);
            depositDTO = (DepositDTO) EntityConversion.convertModel(deposit, depositDTO, EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
        } catch (HibernateException e) {
            throw new Exception(e.getMessage());
        }
        return depositDTO;
    }

    @Override
    public void deleteDeposit(String id) {
        try {
            depDao.deleteDeposit(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(id);
        }
    }

    @Override
    public Long getCommDeposits(String commodityCode) {
        List<Deposit> depositList = new ArrayList<>();

        depositList = depDao.getDepositList(null, null, null, null, commodityCode, null, null, null, null);
        System.out.println(depositList.size());
        return Long.valueOf(depositList.size());
    }

    @Override
    public Long getOrgDeposits(String orgCode) {
        List<Deposit> depositList = new ArrayList<>();

        depositList = depDao.getDepositList(orgCode, null, null, null, null, null, null, null, null);
        System.out.println(depositList.size());
        return Long.valueOf(depositList.size());
    }
}


