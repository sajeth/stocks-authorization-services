package com.saji.stocks.authorization.services;

import com.saji.stocks.authorization.entity.UserEntity;
import com.saji.stocks.authorization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String generateToken(String email, String password) {
        String token = null;
        Optional<UserEntity> user = userRepository.authenticateUser(email, password);
        if (user.isPresent()) {
            UserEntity entity = user.get();
            token = entity.getId() + " " + entity.getName();
        }
        return token;
    }

    public void saveToken(String userId, String token) {
        userRepository.findById(BigInteger.valueOf(Long.parseLong(userId))).ifPresent(val -> {
            val.setToken(token.split(" ")[1]);
            userRepository.save(val);
        });
    }

    public String validateToken(String token) {
        if (userRepository.validateToken(token).isPresent()) {
            return "SUCCESS";
        } else {
            return "INVALID";
        }
    }
}
