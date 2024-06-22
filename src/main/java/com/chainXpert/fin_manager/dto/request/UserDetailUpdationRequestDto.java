package com.chainXpert.fin_manager.dto.request;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@JacksonStdImpl
public class UserDetailUpdationRequestDto {

	private final String firstName;

	private final String lastName;

	private final LocalDate dateOfBirth;

}
