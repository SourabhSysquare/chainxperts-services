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
public class UserDetailsDto {

	private final String emailId;
	private final String firstName;
	private final String lastName;
	private final LocalDate dateOfBirth;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

}
