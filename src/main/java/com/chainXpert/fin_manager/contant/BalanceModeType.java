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
public enum BalanceModeType {

	BANK(1, "BANK"),
	CASH(2, "CASH"),
	RESOURCES_SALEABLE(3, "RESOURCES SALEABLE"),
	MONEY_LENT(4, "MONEY LENT"),
	OTHER(5, "OTHER");

	private final Integer id;
	private final String type;

}
