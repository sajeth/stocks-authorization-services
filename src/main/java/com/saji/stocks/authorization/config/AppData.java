package com.saji.stocks.authorization.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "auth")
public
class AppData {

    @Value("${auth.secret}")
    private String secret;

    @Value("${auth.timeout}")
    private Integer timeout;

    private  Map<String, String> users;

    public void setUsers(Map<String, String> users) {
        this.users = users;
    }

    public String getSecret() {
        return secret;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public Map<String, String> getUsers() {
        return users;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
