package com.saji.stocks.authorization.controller;

import com.saji.stocks.authorization.config.AppData;
import com.saji.stocks.authorization.pojo.User;
import com.saji.stocks.authorization.security.JWTAuthorizationFilter;
import com.saji.stocks.constants.AuthConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(value = "/auth", produces = "application/json")
public class AuthController {


    @Autowired
    AppData appData;

    @PostMapping("/generatetoken")
    public @ResponseBody
    User generateToken(@RequestParam("username") String username, @RequestParam("password") String pwd) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(pwd.replaceAll("(?s).", "*"));
        if (Optional.ofNullable(appData.getUsers().get(username)).isPresent()) {
            user.setToken(getJWTToken(username));
        }
        return user;
    }

    @PostMapping(value = "/validatetoken")
    public @ResponseBody
    Claims tokenValidation(@NotNull HttpServletRequest request) {
        return new JWTAuthorizationFilter(appData.getSecret()).validateToken(request);
    }


    private String getJWTToken(String username) {
        return AuthConstants.PREFIX + Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        AuthorityUtils
                                .commaSeparatedStringToAuthorityList("ROLE_USER").stream()
                                .map(GrantedAuthority::getAuthority)
                                .toList())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + appData.getTimeout()))
                .signWith(SignatureAlgorithm.HS512,
                        appData.getSecret().getBytes()).compact();
    }
}

