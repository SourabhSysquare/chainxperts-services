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
public enum ContextType {

	COMPLETED_TICKET(1, "Completed Ticket"),
	FUTURE_TICKET(2, "Future Ticket"),
	NOTE(3, "Notes");

	private final Integer id;
	private final String name;

}
