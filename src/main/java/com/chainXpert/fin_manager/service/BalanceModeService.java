package com.chainXpert.fin_manager.service;

import com.chainXpert.fin_manager.dto.request.BalanceModeCreationRequestDto;
import com.chainXpert.fin_manager.dto.request.BalanceModeUpdationRequestDto;
import com.chainXpert.fin_manager.dto.response.BalanceModeDto;
import com.chainXpert.fin_manager.enitity.BalanceMode;
import com.chainXpert.fin_manager.repository.BalanceModeRepository;
import com.chainXpert.fin_manager.repository.TotalBalanceRepository;
import com.chainXpert.fin_manager.security.utility.JwtUtils;
import com.chainXpert.fin_manager.utils.CommonUtil;
import com.chainXpert.fin_manager.utils.ResponseUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Service
@AllArgsConstructor
public class BalanceModeService {

    private final BalanceModeRepository balanceModeRepository;

    private final TotalBalanceRepository totalBalanceRepository;

    private final JwtUtils jwtUtils;

    private final ResponseUtils responseUtils;

    public ResponseEntity<?> create(final BalanceModeCreationRequestDto balanceModeCreationRequestDto,
                                    final String token) {
        final var totalBalanceId = jwtUtils.extractTotalBalanceId(CommonUtil.replaceString(token, "Bearer "));
        final var balanceMode = new BalanceMode();
        balanceMode.setActive(true);
        balanceMode.setModeType(balanceModeCreationRequestDto.getModeType());
        balanceMode.setName(balanceModeCreationRequestDto.getName());
        balanceMode.setValue(balanceModeCreationRequestDto.getValue());
        balanceMode.setTotalBalanceId(totalBalanceId.longValue());

        final var savedBalancemode = balanceModeRepository.save(balanceMode);
        return responseUtils.balanceModeSuccessResponse(savedBalancemode.getId());
    }

    public ResponseEntity<?> update(final BalanceModeUpdationRequestDto balanceModeUpdationRequestDto,
                                    final String token) {
        final var totalBalanceId = jwtUtils.extractTotalBalanceId(CommonUtil.replaceString(token, "Bearer "));
        final var balanceMode = balanceModeRepository.findById(balanceModeUpdationRequestDto.getId()).get();

        if (!balanceMode.getTotalBalanceId().equals(totalBalanceId.longValue()))
            return responseUtils.unauthorizedResponse();

        balanceMode.setActive(balanceModeUpdationRequestDto.getIsActive());
        balanceMode.setValue(balanceModeUpdationRequestDto.getValue());

        final var savedBalancemode = balanceModeRepository.save(balanceMode);
        return responseUtils.balanceModeSuccessResponse(savedBalancemode.getId());
    }

    public ResponseEntity<?> retrieve(final String token) {
        final var totalBalanceId = jwtUtils.extractTotalBalanceId(CommonUtil.replaceString(token, "Bearer "));
        final var totalBalance = totalBalanceRepository.findById(totalBalanceId.longValue()).get();
        return ResponseEntity.ok(totalBalance.getBalanceModes().parallelStream()
                .map(balanceMode -> BalanceModeDto.builder().createdAt(balanceMode.getCreatedAt())
                        .id(balanceMode.getId()).isActive(balanceMode.isActive()).modeType(balanceMode.getModeType())
                        .name(balanceMode.getName()).updatedAt(balanceMode.getUpdatedAt()).value(balanceMode.getValue())
                        .build())
                .collect(Collectors.toList()));
    }

}
