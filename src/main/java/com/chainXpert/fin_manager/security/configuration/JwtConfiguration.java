package com.chainXpert.fin_manager.security.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@ConfigurationProperties(prefix = "com.chainxpert.finance.manager")
@Data
public class JwtConfiguration {

    private Configuration jwt = new Configuration();

    @Data
    public class Configuration {
        private String secretKey = "SOMETHING123VERY456STRONG";
    }

}