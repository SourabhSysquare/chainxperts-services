package com.chainXpert.fin_manager.utils;

import org.springframework.stereotype.Component;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Component
public class CommonUtil {

    public static String replaceString(String token, String val) {
        return token.replace(val, "");
    }
}
