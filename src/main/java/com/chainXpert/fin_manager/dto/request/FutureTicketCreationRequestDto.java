package com.chainXpert.fin_manager.dto.request;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;


/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Getter
@Builder
@JacksonStdImpl
public class FutureTicketCreationRequestDto {

	private final Long balanceModeId;

	private final String name;

	private final String description;

	private final String ticketType;

	private final Double value;

	private final LocalDate ticketCompletionDate;

	private final List<TagCreationRequestDto> tags;

}
