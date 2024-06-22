package com.chainXpert.fin_manager.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@UtilityClass
public class SecurityUtils {
    public User convert(com.chainXpert.fin_manager.enitity.User user) {
        return new User(user.getEmailId(), user.getPassword(), List.of());
    }
}
