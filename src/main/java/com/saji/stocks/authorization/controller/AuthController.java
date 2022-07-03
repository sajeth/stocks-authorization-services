package com.saji.stocks.authorization.controller;

import com.saji.stocks.authorization.config.AppData;
import com.saji.stocks.authorization.pojo.User;
import com.saji.stocks.authorization.services.UserService;
import com.saji.stocks.constants.AuthConstants;
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

import static com.saji.stocks.constants.AuthConstants.HEADER;
import static com.saji.stocks.constants.AuthConstants.PREFIX;

@RestController
@RequestMapping(value = "/auth", produces = "application/json")
public class AuthController {


    @Autowired
    AppData appData;

    @Autowired
    UserService userService;

    @PostMapping("/generatetoken")
    public @ResponseBody
    User generateToken(@RequestParam("email") String email, @RequestParam("password") String pwd) {
        User user = new User();
        user.setUsername(email);
        user.setPassword(pwd.replaceAll("(?s).", "*"));
        String token = userService.generateToken(email, pwd);
        if (Optional.ofNullable(token).isPresent()) {
            String id = token.split(" ")[0];
            user.setToken(getJWTToken(token.replace(" ", "")));
            userService.saveToken(id, user.getToken());
        }
        return user;
    }

    @PostMapping(value = "/validatetoken")
    public @ResponseBody
    String tokenValidation(@NotNull HttpServletRequest request) {
        return userService.validateToken(request.getHeader(HEADER).replace(PREFIX, ""));
    }


    private String getJWTToken(String token) {
        return AuthConstants.PREFIX + Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(token)
                .claim("authorities",
                        AuthorityUtils
                                .commaSeparatedStringToAuthorityList("ROLE_USER").stream()
                                .map(GrantedAuthority::getAuthority)
                                .toList())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + appData.getTimeout()))
                .signWith(SignatureAlgorithm.HS512,
                        token.getBytes()).compact();
    }
}

