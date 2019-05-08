package com.dit.app.demo.deposit.service.impl;

import com.dit.app.demo.deposit.dao.QuantityLedgerDao;
import com.dit.app.demo.deposit.model.QuantityLedger;
import com.dit.app.demo.deposit.model.dto.QuantityLedgerDTO;
import com.dit.app.demo.deposit.service.QuantityLedgerService;
import com.dit.app.demo.helper.EntityConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuantityLedgerServiceImpl implements QuantityLedgerService {

    @Autowired
    QuantityLedgerDao quantityLedgerDao;

    @Override
    public List<QuantityLedgerDTO> getQuantityLedgerList(String warehouseCode, String warehouseName, String clientId, String clientName, String commodityCode, String commodityName, ZonedDateTime startDate, ZonedDateTime endDate) {
        List<QuantityLedgerDTO> quantityLedgerDTOList = new ArrayList<>();
        List<QuantityLedger> quantityLedgerList = new ArrayList<>();

        quantityLedgerList = quantityLedgerDao.getQuantityLedgerList(warehouseCode, warehouseName, clientId, clientName, commodityCode, commodityName, startDate, endDate);

        for (QuantityLedger quantityLedger : quantityLedgerList) {
            QuantityLedgerDTO dto = new QuantityLedgerDTO();
            dto = (QuantityLedgerDTO) EntityConversion.convertModel(quantityLedger, dto, EntityConversion.ConversionEnum.ENTITYTODTO.ordinal());
            quantityLedgerDTOList.add(dto);
        }

        return quantityLedgerDTOList;
    }
}
