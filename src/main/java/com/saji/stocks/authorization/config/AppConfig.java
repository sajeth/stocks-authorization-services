package com.saji.stocks.authorization.config;

import com.saji.stocks.authorization.security.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
class AppConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppData appData;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(appData.getSecret()), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/*").permitAll()
                .antMatchers(HttpMethod.GET, "/actuator/*", "/swagger-ui/*", "/v3/*", "/v3/api-docs/*").permitAll()
                .anyRequest().authenticated();
    }
}
