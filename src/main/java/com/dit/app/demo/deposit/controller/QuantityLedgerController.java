package com.dit.app.demo.deposit.controller;

import com.dit.app.demo.deposit.model.dto.QuantityLedgerDTO;
import com.dit.app.demo.deposit.service.QuantityLedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/qtyLedger")
public class QuantityLedgerController {

    @Autowired
    QuantityLedgerService quantityLedgerService;

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<List<QuantityLedgerDTO>> depositList(@RequestParam(required = false) String warehouseCode,
                                                               @RequestParam(required = false) String warehouseName,
                                                               @RequestParam(required = false) String commodityCode,
                                                               @RequestParam(required = false) String commodityName,
                                                               @RequestParam(required = false) String clientId,
                                                               @RequestParam(required = false) String clientName,
                                                               @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
                                                               @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDate) {
        List<QuantityLedgerDTO> quantityLedgerDTOList = new ArrayList<>();

        quantityLedgerDTOList = quantityLedgerService.getQuantityLedgerList(warehouseCode, warehouseName, clientId, clientName, commodityCode, commodityName, startDate, endDate);

        return new ResponseEntity<>(quantityLedgerDTOList, HttpStatus.OK);
    }
}
