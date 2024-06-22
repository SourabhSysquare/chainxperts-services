package com.chainXpert.fin_manager.service;

import com.chainXpert.fin_manager.dto.request.MonthlySpendingThresholdLimitRequestDto;
import com.chainXpert.fin_manager.repository.CurrentMonthlySpendingThresholdLimitRepository;
import com.chainXpert.fin_manager.security.utility.JwtUtils;
import com.chainXpert.fin_manager.utils.ResponseUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Service
@AllArgsConstructor
public class CurrentMonthlySpendingThresholdLimitService {

    private final CurrentMonthlySpendingThresholdLimitRepository currentMonthlySpendingThresholdLimitRepository;
    private final JwtUtils jwtUtils;
    private final ResponseUtils responseUtils;

    public ResponseEntity<?> update(final MonthlySpendingThresholdLimitRequestDto monthlySpendingThresholdLimitRequest,
                                    final String token) {
        final var currentMonthlySpendingThresholdLimit = currentMonthlySpendingThresholdLimitRepository
                .findByUserId(jwtUtils.extractUserId(token.replace("Bearer ", "")).longValue()).get();

        currentMonthlySpendingThresholdLimit.setIsActive(monthlySpendingThresholdLimitRequest.getActive());
        currentMonthlySpendingThresholdLimit.setLimitValue(monthlySpendingThresholdLimitRequest.getLimitValue());

        currentMonthlySpendingThresholdLimitRepository.save(currentMonthlySpendingThresholdLimit);
        return responseUtils.monthlySpendingUpdationSuccessResponse();
    }

}
