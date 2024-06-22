package com.chainXpert.fin_manager.dto.request;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Getter
@Builder
@JacksonStdImpl
public class UserCreationRequestDto {

	private final String emailId;

	private final String password;

	private final String firstName;

	private final String lastName;

	private final LocalDate dateOfBirth;

}
