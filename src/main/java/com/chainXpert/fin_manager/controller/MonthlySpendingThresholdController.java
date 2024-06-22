package com.chainXpert.fin_manager.controller;

import com.chainXpert.fin_manager.dto.request.MonthlySpendingThresholdLimitRequestDto;
import com.chainXpert.fin_manager.dto.response.SpendingThresholdRecordDto;
import com.chainXpert.fin_manager.service.CurrentMonthlySpendingThresholdLimitService;
import com.chainXpert.fin_manager.service.SpendingThresholdRecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/monthly-spending-threshold")
public class MonthlySpendingThresholdController {

    private final CurrentMonthlySpendingThresholdLimitService currentMonthlySpendingThresholdLimitService;

    private final SpendingThresholdRecordService spendingThresholdRecordService;

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> monthlySpendingThresholdUpdationHandler(
            @RequestBody(required = true) final MonthlySpendingThresholdLimitRequestDto monthlySpendingThresholdLimitRequest,
            @RequestHeader(name = "Authorization", required = true)  final String token) {
        return currentMonthlySpendingThresholdLimitService.update(monthlySpendingThresholdLimitRequest, token);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<SpendingThresholdRecordDto>> boom(
            @RequestHeader(name = "Authorization", required = true)  final String token) {
        return spendingThresholdRecordService.retreivePastRecords(token);
    }

}
