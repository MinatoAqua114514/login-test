package com.minatoaqua.logintest.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    private String status;
    private String message;
    private String username;
    private String email;


    public RegisterResponse(String status, String message, String username, String email) {
        this.status = status;
        this.message = message;
        this.username = username;
        this.email = email;
    }

}
