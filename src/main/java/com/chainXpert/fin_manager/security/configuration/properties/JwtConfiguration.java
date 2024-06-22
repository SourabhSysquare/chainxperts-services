package com.chainXpert.fin_manager.security.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@ConfigurationProperties(prefix = "com.chainXpert.auction")
@Data
public class JwtConfiguration {

    private Configuration jwt = new Configuration();

    @Data
    public class Configuration {
        private String secretKey;
    }

}