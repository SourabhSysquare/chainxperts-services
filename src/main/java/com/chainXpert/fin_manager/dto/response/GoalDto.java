package com.chainXpert.fin_manager.dto.response;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Getter
@Builder
@JacksonStdImpl
public class GoalDto {

	private final Long id;
	private final String title;
	private final String description;
	private final Boolean isActive;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

}
