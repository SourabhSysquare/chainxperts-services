package com.chainXpert.fin_manager.dto.response;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Getter
@Builder
@JacksonStdImpl
public class TicketDto {

	private final Long id;
	private final BalanceModeDto balanceMode;
	private final String name;
	private final String description;
	private final String ticketType1;
	private final String ticketType2;
	private final Double value;
	private final LocalDate ticketCompletionDate;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

}
