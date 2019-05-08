package com.dit.app.demo.deposit.controller;

import com.dit.app.demo.deposit.model.dto.WithdrawalDTO;
import com.dit.app.demo.deposit.service.WithdrawalService;
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
@RequestMapping("api/v1/withdraw")
public class WithdrawalController {

    @Autowired
    WithdrawalService withdrawalService;

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<List<WithdrawalDTO>> withdrawalList(@RequestParam(required = false) String withdrawalId,
                                                              @RequestParam(required = false) String depositId,
                                                              @RequestParam(required = false) String warehouseCode,
                                                              @RequestParam(required = false) String warehouseName,
                                                              @RequestParam(required = false) String clientId,
                                                              @RequestParam(required = false) String clientName,
                                                              @RequestParam(required = false) String commodityCode,
                                                              @RequestParam(required = false) String commodityName,
                                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
                                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDate) {
        List<WithdrawalDTO> withdrawalList = new ArrayList<>();

        withdrawalList = withdrawalService.getWithdrawalList(withdrawalId,depositId, warehouseCode, warehouseName, clientId, clientName, commodityCode, commodityName, startDate, endDate);

        return new ResponseEntity<>(withdrawalList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<Object> getWihdrawalDetails(@PathVariable("id") String id) {
        WithdrawalDTO withdrawalDTO = withdrawalService.getWithdrawalDtls(id);

        if (withdrawalDTO != null) {
            return new ResponseEntity<>(withdrawalDTO, HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("" + id);
        }

    }

    @RequestMapping(method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<WithdrawalDTO> saveWithdrawal(@RequestBody WithdrawalDTO withdrawalDTO) throws Exception {
        withdrawalDTO = withdrawalService.saveWithdrawal(withdrawalDTO);
        return new ResponseEntity<>(withdrawalDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = {"application/json"})
    public ResponseEntity<WithdrawalDTO> updateWithdrawal(@RequestBody WithdrawalDTO withdrawalDTO) throws Exception {
        withdrawalDTO = withdrawalService.updateWithdrawal(withdrawalDTO);
        return new ResponseEntity<>(withdrawalDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/json"})
    public ResponseEntity<Object> deleteWithdrawal(@PathVariable("id") String id) {
        try {
            withdrawalService.deleteWithdrawal(id);
            return new ResponseEntity<>(new HashMap().put("msg", "Record Deleted Successfully"), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(id);
        }
    }
}
