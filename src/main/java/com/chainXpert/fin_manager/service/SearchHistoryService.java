package com.chainXpert.fin_manager.service;

import com.chainXpert.fin_manager.dto.request.BalanceModeCreationRequestDto;
import com.chainXpert.fin_manager.dto.request.SearchHistoryDto;
import com.chainXpert.fin_manager.enitity.BalanceMode;
import com.chainXpert.fin_manager.enitity.SearchHistory;
import com.chainXpert.fin_manager.enitity.User;
import com.chainXpert.fin_manager.repository.SearchHistoryRepository;
import com.chainXpert.fin_manager.repository.UserRepository;
import com.chainXpert.fin_manager.security.utility.JwtUtils;
import com.chainXpert.fin_manager.utils.CommonUtil;
import com.chainXpert.fin_manager.utils.ResponseUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Service
@AllArgsConstructor
public class SearchHistoryService {

    private final SearchHistoryRepository searchHistoryRepository;
    private final UserRepository userRepository;
    private final ResponseUtils responseUtils;
    private final JwtUtils jwtUtils;

    public void upsert(final SearchHistoryDto dto, final String token) {
        final var userId = jwtUtils.extractUserId(CommonUtil.replaceString(token, "Bearer "));
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            final User user = userOptional.get();
            SearchHistory searchHistory = new SearchHistory();
            searchHistory.setUserId(userId);
            searchHistory.setPrompt(dto.getPrompt());
            searchHistory.setTitle(dto.getPrompt());
            searchHistory.setContent(dto.getContent());
            searchHistory.setCreatedAt(LocalDateTime.now());
            searchHistoryRepository.save(searchHistory);
        }
    }

    public ResponseEntity<?> getUserData(String token) {
        final var userId = jwtUtils.extractUserId(CommonUtil.replaceString(token, "Bearer "));
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            final User user = userOptional.get();
            return ResponseEntity.ok(searchHistoryRepository.findAllByUserId(user.getId()));
        }
        return responseUtils.unauthorizedResponse();
    }
}
