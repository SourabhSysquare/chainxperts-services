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
public enum ApiPathExclusion {

    WEBJARS("/webjars/**"),
    MASTER_RESOURCE("/master/**"),
    USER_LOGIN_API("/login");


    private final String path;
}
