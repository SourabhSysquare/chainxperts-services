package com.chainXpert.fin_manager.dto.request;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import lombok.Builder;
import lombok.Getter;


/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Getter
@Builder
@JacksonStdImpl
public class GoalUpdationRequestDto {

	private final Long id;

	private final String description;

	private final Boolean isActive;

}
