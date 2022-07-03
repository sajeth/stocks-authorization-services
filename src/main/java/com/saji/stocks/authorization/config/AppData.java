package com.saji.stocks.authorization.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "auth")
public
class AppData {

    @Value("${auth.secret}")
    private String secret;

    @Value("${auth.timeout}")
    private Integer timeout;


    public String getSecret() {
        return secret;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
