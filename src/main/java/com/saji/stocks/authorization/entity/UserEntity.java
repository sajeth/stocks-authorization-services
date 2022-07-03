package com.saji.stocks.authorization.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class UserEntity extends BaseEntity {
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "PIN", nullable = false)
    private String pin;
    @Column(name = "TOKEN", nullable = true)
    private String token;

    public UserEntity() {
    }

    public UserEntity(String password, String email, String pin, String token) {
        this.password = password;
        this.pin = pin;
        this.email = email;
        this.token = token;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
