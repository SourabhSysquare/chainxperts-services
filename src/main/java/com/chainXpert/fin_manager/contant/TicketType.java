package com.chainXpert.fin_manager.contant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Getter
@AllArgsConstructor
public enum TicketType {

	EXPENSE(1, "EXPENSE"),
	GAIN(2, "GAIN");

	private final Integer id;
	private final String type;

}
