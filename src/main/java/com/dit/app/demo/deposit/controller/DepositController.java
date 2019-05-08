package com.dit.app.demo.deposit.controller;

import com.dit.app.demo.deposit.model.dto.DepositDTO;
import com.dit.app.demo.deposit.service.DepositService;
import com.dit.app.demo.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/deposit")

public class DepositController {

    @Autowired
    DepositService depositService;


    @RequestMapping(method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<List<DepositDTO>> depositList(@RequestParam(required = false) String warehouseCode,
                                                        @RequestParam(required = false) String warehouseName,
                                                        @RequestParam(required = false) String commodityCode,
                                                        @RequestParam(required = false) String commodityName,
                                                        @RequestParam(required = false) String clientId,
                                                        @RequestParam(required = false) String clientName,
                                                        @RequestParam(required = false) String status,
                                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
                                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDate) {
        List<DepositDTO> depositList = new ArrayList<>();

        depositList = depositService.getDepositList(warehouseCode, warehouseName, clientId, clientName, commodityCode, commodityName, startDate, endDate, status);

        return new ResponseEntity<>(depositList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<Object> getDeposit(@PathVariable("id") String id) {
        DepositDTO deposit = depositService.getDeposit(id);

        if (deposit != null) {
            return new ResponseEntity<>(deposit, HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("" + id);
        }

    }

    @RequestMapping(method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<DepositDTO> saveDeposit(@RequestBody DepositDTO deposit) throws Exception {
        deposit = depositService.saveDeposit(deposit);
        return new ResponseEntity<>(deposit, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = {"application/json"})
    public ResponseEntity<DepositDTO> updateDeposit(@RequestBody DepositDTO deposit) throws Exception {
        deposit = depositService.updateDeposit(deposit);
        return new ResponseEntity<>(deposit, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/json"})
    public ResponseEntity<Object> deleteDeposit(@PathVariable("id") String id) {
        try {
            depositService.deleteDeposit(id);
            return new ResponseEntity<>(new HashMap().put("msg", "Record Deleted Successfully"), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(id);
        }
    }

    @RequestMapping(value = "commDeposit/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<Object> getDeposits(@PathVariable("id") String commodityCode) {
        try {
            Long count = depositService.getCommDeposits(commodityCode);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(commodityCode);
        }
    }

    @RequestMapping(value = "orgDeposit/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<Object> getOrgDeposits(@PathVariable("id") String orgCode) {
        try {
            Long count = depositService.getOrgDeposits(orgCode);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(orgCode);
        }
    }
}

