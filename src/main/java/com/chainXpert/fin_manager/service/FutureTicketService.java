package com.chainXpert.fin_manager.service;

import com.chainXpert.fin_manager.dto.request.FutureTicketCreationRequestDto;
import com.chainXpert.fin_manager.dto.response.BalanceModeDto;
import com.chainXpert.fin_manager.dto.response.TicketDto;
import com.chainXpert.fin_manager.enitity.FutureTicket;
import com.chainXpert.fin_manager.enitity.Tag;
import com.chainXpert.fin_manager.enitity.TicketTagMapping;
import com.chainXpert.fin_manager.repository.FutureTicketRepository;
import com.chainXpert.fin_manager.repository.TagRepository;
import com.chainXpert.fin_manager.repository.TicketTagMappingRepository;
import com.chainXpert.fin_manager.repository.UserRepository;
import com.chainXpert.fin_manager.security.utility.JwtUtils;
import com.chainXpert.fin_manager.utils.CommonUtil;
import com.chainXpert.fin_manager.utils.ResponseUtils;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
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
public class FutureTicketService {

    private final FutureTicketRepository futureTicketRepository;

    private final UserRepository userRepository;

    private final TagRepository tagRepository;

    private final TicketTagMappingRepository ticketTagMappingRepository;

    private final JwtUtils jwtUtils;

    private final ResponseUtils responseUtils;

    public ResponseEntity<?> create(final FutureTicketCreationRequestDto futureTicketCreationRequest,
                                    final String token) {
        final var userId = jwtUtils.extractUserId(CommonUtil.replaceString(token, "Bearer "));
        final var futureTicket = getFutureTicket(futureTicketCreationRequest, userId.longValue());

        final var savedFutureTicket = futureTicketRepository.save(futureTicket);

        futureTicketCreationRequest.getTags().forEach(ticketTag -> {
            final var tag = tagRepository.findByName(ticketTag.getName().toUpperCase()).orElse(new Tag());
            tag.setName(ticketTag.getName().toUpperCase());
            final var savedTag = tagRepository.save(tag);

            final var ticketTagMapping = new TicketTagMapping();
            ticketTagMapping.setTagId(savedTag.getId());
            ticketTagMapping.setTicketId(savedFutureTicket.getId());
            ticketTagMapping.setTicketType("FUTURE");

            ticketTagMappingRepository.save(ticketTagMapping);

        });

        return responseUtils.futureTicketSuccessResponse(savedFutureTicket.getId());
    }

    @NotNull
    private static FutureTicket getFutureTicket(FutureTicketCreationRequestDto futureTicketCreationRequest, Long userId) {
        final var futureTicket = new FutureTicket();

        futureTicket.setBalanceModeId(futureTicketCreationRequest.getBalanceModeId());
        futureTicket.setDescription(futureTicketCreationRequest.getDescription());
        futureTicket.setName(futureTicketCreationRequest.getName());
        if (futureTicketCreationRequest.getTicketCompletionDate() != null)
            futureTicket.setTicketCompletionDate(futureTicketCreationRequest.getTicketCompletionDate());
        futureTicket.setValue(futureTicketCreationRequest.getValue());
        futureTicket.setTicketType(futureTicketCreationRequest.getTicketType());
        futureTicket.setUserId(userId);
        return futureTicket;
    }

    public ResponseEntity<?> delete(final Long ticketId, final String token) {
        final var futureTicketId = futureTicketRepository.findById(ticketId).get();
        final var userId = jwtUtils.extractUserId(CommonUtil.replaceString(token, "Bearer "));

        if (!futureTicketId.getUserId().equals(userId))
            responseUtils.unauthorizedResponse();

        futureTicketRepository.deleteById(ticketId);
        return responseUtils.futureTicketDeletionResponse();
    }

    public ResponseEntity<?> retreiveExpenses(final String token) {
        final var userId = jwtUtils.extractUserId(CommonUtil.replaceString(token, "Bearer "));
        final var user = userRepository.findById(userId.longValue()).get();
        return ResponseEntity.ok(user.getFutureTickets().parallelStream()
                .filter(completedTicket -> completedTicket.getTicketType().equalsIgnoreCase("expense"))
                .map(completedTicket -> {
                    final var balanceMode = completedTicket.getBalanceMode();
                    final var balanceModeDto = BalanceModeDto.builder().createdAt(balanceMode.getCreatedAt())
                            .id(balanceMode.getId()).isActive(balanceMode.isActive())
                            .modeType(balanceMode.getModeType()).name(balanceMode.getName())
                            .updatedAt(balanceMode.getUpdatedAt()).value(balanceMode.getValue()).build();
                    return TicketDto.builder().createdAt(completedTicket.getCreatedAt())
                            .description(completedTicket.getDescription()).id(completedTicket.getId())
                            .name(completedTicket.getName())
                            .ticketCompletionDate(completedTicket.getTicketCompletionDate())
                            .updatedAt(completedTicket.getUpdatedAt()).value(completedTicket.getValue())
                            .ticketType1("FUTURE").ticketType2(completedTicket.getTicketType())
                            .balanceMode(balanceModeDto).build();
                }).collect(Collectors.toList()));
    }

    public ResponseEntity<?> retreiveGains(final String token) {
        final var userId = jwtUtils.extractUserId(CommonUtil.replaceString(token, "Bearer "));
        final var user = userRepository.findById(userId.longValue()).get();
        return ResponseEntity.ok(user.getFutureTickets().parallelStream()
                .filter(completedTicket -> completedTicket.getTicketType().equalsIgnoreCase("gain"))
                .map(completedTicket -> {
                    final var balanceMode = completedTicket.getBalanceMode();
                    final var balanceModeDto = BalanceModeDto.builder().createdAt(balanceMode.getCreatedAt())
                            .id(balanceMode.getId()).isActive(balanceMode.isActive())
                            .modeType(balanceMode.getModeType()).name(balanceMode.getName())
                            .updatedAt(balanceMode.getUpdatedAt()).value(balanceMode.getValue()).build();
                    return TicketDto.builder().createdAt(completedTicket.getCreatedAt())
                            .description(completedTicket.getDescription()).id(completedTicket.getId())
                            .name(completedTicket.getName())
                            .ticketCompletionDate(completedTicket.getTicketCompletionDate())
                            .updatedAt(completedTicket.getUpdatedAt()).value(completedTicket.getValue())
                            .ticketType1("FUTURE").ticketType2(completedTicket.getTicketType())
                            .balanceMode(balanceModeDto).build();
                }).collect(Collectors.toList()));
    }

}
