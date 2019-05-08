package com.dit.app.demo.helper;

import com.dit.app.demo.deposit.model.QuantityLedger;
import com.dit.app.demo.deposit.model.QuantityLedgerId;
import com.dit.app.demo.deposit.model.dto.QuantityLedgerDTO;
import org.modelmapper.ModelMapper;

public class EntityConversion {

    public static <T> T convertModel(T entityObj, T dtoObj, int i) {
        ConversionEnum conEnum = ConversionEnum.values()[i];
        ModelMapper modelMapper = new ModelMapper();
        switch (conEnum) {
            case ENTITYTODTO:
                dtoObj = (T) modelMapper.map(entityObj, dtoObj.getClass());
                if (dtoObj instanceof QuantityLedgerDTO && entityObj instanceof QuantityLedger) {
                    QuantityLedger quantityLedger = (QuantityLedger) entityObj;
                    QuantityLedgerId idObj = quantityLedger.getQtyId();
                    ((QuantityLedgerDTO) dtoObj).setCommodityCode(idObj.getCommodityCode());
                    ((QuantityLedgerDTO) dtoObj).setClientId(idObj.getClientId());
                    ((QuantityLedgerDTO) dtoObj).setWarehouseCode(idObj.getWarehouseCode());
                }
                return dtoObj;

            case DTOTOENTITY:
                entityObj = (T) modelMapper.map(dtoObj, entityObj.getClass());
                if (dtoObj instanceof QuantityLedgerDTO && entityObj instanceof QuantityLedger){
                    QuantityLedgerDTO quantityLedgerDTO = (QuantityLedgerDTO)dtoObj;
                    QuantityLedgerId idObj = new QuantityLedgerId();
                    idObj.setWarehouseCode(quantityLedgerDTO.getWarehouseCode());
                    idObj.setCommodityCode(quantityLedgerDTO.getCommodityCode());
                    idObj.setClientId(quantityLedgerDTO.getClientId());
                    ((QuantityLedger) entityObj).setQtyId(idObj);
                }
                return entityObj;

            default:
                return null;
        }
    }

    public static enum ConversionEnum {
        ENTITYTODTO, DTOTOENTITY;
    }


}
