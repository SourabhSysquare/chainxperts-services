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
public class BalanceModeDto {

	private final Long id;
	private final String modeType;
	private final String name;
	private final Double value;
	private final boolean isActive;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

}
