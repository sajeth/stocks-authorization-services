package com.saji.stocks.authorization.repository;

import com.saji.stocks.authorization.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends org.springframework.data.jpa.repository.JpaRepository<UserEntity, java.math.BigInteger> {
    @Query("from UserEntity where email = ?1 and password = ?2")
    Optional<UserEntity> authenticateUser(String email, String password);

    @Query("from UserEntity where token = ?1")
    Optional<UserEntity> validateToken(String token);
}
