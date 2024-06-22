package com.chainXpert.fin_manager.service;

import com.chainXpert.fin_manager.contant.TicketType;
import com.chainXpert.fin_manager.dto.response.SpendingThresholdRecordDto;
import com.chainXpert.fin_manager.enitity.CompletedTicket;
import com.chainXpert.fin_manager.enitity.SpendingThresholdRecord;
import com.chainXpert.fin_manager.repository.CompletedTicketRepository;
import com.chainXpert.fin_manager.repository.CurrentMonthlySpendingThresholdLimitRepository;
import com.chainXpert.fin_manager.repository.SpendingThresholdRecordRepository;
import com.chainXpert.fin_manager.repository.UserRepository;
import com.chainXpert.fin_manager.security.utility.JwtUtils;
import com.chainXpert.fin_manager.utils.CommonUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Service
@AllArgsConstructor
public class SpendingThresholdRecordService {

    private final SpendingThresholdRecordRepository spendingThresholdRecordRepository;
    private final CurrentMonthlySpendingThresholdLimitRepository currentMonthSpendingRepository;
    private final CompletedTicketRepository completedTicketRepository;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public ResponseEntity<List<SpendingThresholdRecordDto>> retreivePastRecords(final String token) {
        Long uId = jwtUtils.extractUserId(CommonUtil.replaceString(token, "Bearer "));
        return ResponseEntity.ok(spendingThresholdRecordRepository
                .findByUserId(uId.longValue()).parallelStream()
                .map(pastSpendingRecord -> SpendingThresholdRecordDto.builder().id(pastSpendingRecord.getId())
                        .limitValue(pastSpendingRecord.getLimitValue()).month(pastSpendingRecord.getMonth())
                        .valueSpent(pastSpendingRecord.getValueSpent()).year(pastSpendingRecord.getYear()).build())
                .collect(Collectors.toList()));
    }

    public void calulate() {
        userRepository.findAll().forEach(user -> {
            final var currentMonthSpending = currentMonthSpendingRepository.findByUserId(user.getId());
            final var previousMonthDate = LocalDate.now().minusDays(1);

            if (currentMonthSpending.isPresent() && currentMonthSpending.get().getIsActive()) {
                final var spendingThresholdRecord = new SpendingThresholdRecord();
                spendingThresholdRecord.setLimitValue(currentMonthSpending.get().getLimitValue());
                spendingThresholdRecord.setMonth(Month.of(previousMonthDate.getMonthValue()).toString());
                spendingThresholdRecord.setUserId(user.getId());
                spendingThresholdRecord.setYear(String.valueOf(previousMonthDate.getYear()));

                Double totalMonthExpense = 0.0;

                final var monthlyExpenseTicketList = completedTicketRepository
                        .findByUserIdAndCreatedAtBetween(user.getId(),
                                LocalDate.of(previousMonthDate.getYear(), previousMonthDate.getMonthValue(), 1),
                                previousMonthDate)
                        .parallelStream().filter(completedTicket -> completedTicket.getTicketType()
                                .equalsIgnoreCase(TicketType.EXPENSE.getType()))
                        .collect(Collectors.toList());
                ;

                for (CompletedTicket completedTicket : monthlyExpenseTicketList) {
                    totalMonthExpense += completedTicket.getValue();
                }

                spendingThresholdRecord.setValueSpent(totalMonthExpense);
                spendingThresholdRecordRepository.save(spendingThresholdRecord);
            }

        });
    }

}
