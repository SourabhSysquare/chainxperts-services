package com.chainXpert.fin_manager.service;

import com.chainXpert.fin_manager.dto.request.GoalCreationRequestDto;
import com.chainXpert.fin_manager.dto.request.GoalUpdationRequestDto;
import com.chainXpert.fin_manager.dto.response.GoalDto;
import com.chainXpert.fin_manager.enitity.Goal;
import com.chainXpert.fin_manager.repository.GoalRepository;
import com.chainXpert.fin_manager.repository.UserRepository;
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
public class GoalService {

    private final GoalRepository goalRepository;

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    private final ResponseUtils responseUtils;

    public ResponseEntity<?> create(final GoalCreationRequestDto goalCreationRequest, final String token) {
        final var goal = new Goal();
        goal.setActive(true);
        goal.setDescription(goalCreationRequest.getDescription());
        goal.setTitle(goalCreationRequest.getTitle());
        goal.setUserId(jwtUtils.extractUserId(CommonUtil.replaceString(token, "Bearer ")).longValue());
        final var savedGoal = goalRepository.save(goal);
        return responseUtils.goalSuccessResponse(savedGoal.getId());
    }

    public ResponseEntity<?> update(final GoalUpdationRequestDto goalUpdationRequest, final String token) {
        final var goal = goalRepository.findById(goalUpdationRequest.getId()).get();
        goal.setActive(goalUpdationRequest.getIsActive());
        goal.setDescription(goalUpdationRequest.getDescription());
        final var savedGoal = goalRepository.save(goal);
        return responseUtils.goalSuccessResponse(savedGoal.getId());
    }

    public ResponseEntity<?> retreive(final String token) {
        final var user = userRepository.findById(jwtUtils.extractUserId(CommonUtil.replaceString(token, "Bearer ")).longValue()).get();
        return ResponseEntity.ok(user.getGoals().parallelStream()
                .map(goal -> GoalDto.builder().createdAt(goal.getCreatedAt()).description(goal.getDescription())
                        .id(goal.getId()).isActive(goal.isActive()).title(goal.getTitle())
                        .updatedAt(goal.getUpdatedAt()).build())
                .collect(Collectors.toList()));
    }

}
