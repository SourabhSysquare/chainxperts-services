package com.chainXpert.fin_manager.service;

import com.chainXpert.fin_manager.dto.request.UserCreationRequestDto;
import com.chainXpert.fin_manager.dto.request.UserDetailUpdationRequestDto;
import com.chainXpert.fin_manager.dto.request.UserLoginRequestDto;
import com.chainXpert.fin_manager.dto.request.UserPasswordUpdationRequestDto;
import com.chainXpert.fin_manager.dto.response.UserDetailsDto;
import com.chainXpert.fin_manager.enitity.CurrentMonthlySpendingThresholdLimit;
import com.chainXpert.fin_manager.enitity.TotalBalance;
import com.chainXpert.fin_manager.enitity.User;
import com.chainXpert.fin_manager.repository.CurrentMonthlySpendingThresholdLimitRepository;
import com.chainXpert.fin_manager.repository.TotalBalanceRepository;
import com.chainXpert.fin_manager.repository.UserRepository;
import com.chainXpert.fin_manager.security.utility.JwtUtils;
import com.chainXpert.fin_manager.utils.CommonUtil;
import com.chainXpert.fin_manager.utils.ResponseUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final TotalBalanceRepository totalBalanceRepository;

    private final PasswordEncoder passwordEncoder;

    private final ResponseUtils responseUtils;

    private final JwtUtils jwtUtils;

    private final CurrentMonthlySpendingThresholdLimitRepository currentMonthlySpendingThresholdLimitRepository;

    public boolean userExists(final String emailId) {
//        return userRepository.findByEmailId(emailId).isPresent() ? true : false;
        return true;
    }

    public ResponseEntity<?> retreive(String token) {
        final var userId = jwtUtils.extractUserId(CommonUtil.replaceString(token, "Bearer "));
        final var user = userRepository.findById(userId.longValue()).get();
        return ResponseEntity.ok(UserDetailsDto.builder().createdAt(user.getCreatedAt())
                .dateOfBirth(user.getDateOfBirth()).emailId(user.getEmailId()).firstName(user.getFirstName())
                .lastName(user.getLastName()).updatedAt(user.getUpdatedAt()).build());
    }

    public ResponseEntity<?> createUser(final UserCreationRequestDto userCreationRequest) {

        if (userExists(userCreationRequest.getEmailId()))
            return responseUtils.duplicateEmailIdResponse();

        final var user = new User();
        user.setEmailId(userCreationRequest.getEmailId());
        user.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
        user.setFirstName(userCreationRequest.getFirstName());
        user.setLastName(userCreationRequest.getLastName());
        user.setDateOfBirth(userCreationRequest.getDateOfBirth());

        final var savedUser = userRepository.save(user);

        final var totalBalance = new TotalBalance();
        totalBalance.setUserId(savedUser.getId());
        totalBalanceRepository.save(totalBalance);

        final var currentMonthlySpendingThresholdLimit = new CurrentMonthlySpendingThresholdLimit();
        currentMonthlySpendingThresholdLimit.setUserId(savedUser.getId());
        currentMonthlySpendingThresholdLimit.setLimitValue(0.0);
        currentMonthlySpendingThresholdLimit.setIsActive(false);
        currentMonthlySpendingThresholdLimitRepository.save(currentMonthlySpendingThresholdLimit);

        return responseUtils.userCreationSuccessResponse();
    }

    public ResponseEntity<?> login(final UserLoginRequestDto userLoginRequestDto) {
        User user = userRepository.findByEmailId(userLoginRequestDto.getEmailId());
        if (Objects.isNull(user)) {
            return responseUtils.invalidEmailIdResponse();
        }
        if (passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())) {
            Optional<TotalBalance> totalBalanceOptional = totalBalanceRepository.findByUserId(user.getId());
            TotalBalance totalBal = null;
            if (totalBalanceOptional.isPresent()) {
                totalBal = totalBalanceOptional.get();
            }
            final var jwtToken = jwtUtils.generateToken(user, totalBal != null ? totalBal.getId() : 0);
            return responseUtils.loginSuccessResponse(jwtToken, user);
        }
        return responseUtils.invalidPasswordResponse();
    }

    public ResponseEntity<?> updatePassword(final UserPasswordUpdationRequestDto userPasswordUpdationRequestDto,
                                            final String token) {
        final var loggedInUserId = jwtUtils.extractUserId(CommonUtil.replaceString(token, "Bearer "));
        final var user = userRepository.findById(loggedInUserId.longValue()).get();

        if (!passwordEncoder.matches(userPasswordUpdationRequestDto.getOldPassword(), user.getPassword()))
            return responseUtils.invalidPasswordResponse();

        user.setPassword(passwordEncoder.encode(userPasswordUpdationRequestDto.getNewPassword()));
        userRepository.save(user);

        return responseUtils.passwordUpdationSuccessResponse();
    }

    public ResponseEntity<?> deleteAccount(final String token) {
        final var userId = jwtUtils.extractUserId(CommonUtil.replaceString(token, "Bearer "));
        final var totalBalance = totalBalanceRepository.findByUserId(userId.longValue()).get();
        totalBalanceRepository.deleteById(totalBalance.getId());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> update(final UserDetailUpdationRequestDto userDetailUpdationRequest, final String token) {
        final var loggedInUserId = jwtUtils.extractUserId(CommonUtil.replaceString(token, "Bearer "));
        final var user = userRepository.findById(loggedInUserId.longValue()).get();

        user.setFirstName(userDetailUpdationRequest.getFirstName());
        user.setLastName(userDetailUpdationRequest.getLastName());
        user.setDateOfBirth(userDetailUpdationRequest.getDateOfBirth());
        userRepository.save(user);

        return responseUtils.profileDetailUpdationSuccessResponse();
    }

}
