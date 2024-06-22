package com.chainXpert.fin_manager.scheduler;

import com.chainXpert.fin_manager.service.SpendingThresholdRecordService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Component
@AllArgsConstructor
public class MonthlySpendingRecordCalculationScheduler {

    private final SpendingThresholdRecordService spendingThresholdRecordService;

    @Scheduled(cron = "0 0 12 1 * ?")
    public void monthlySpendingRecordCalculationJob() {
        spendingThresholdRecordService.calulate();
    }

}
