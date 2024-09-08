package com.minatoaqua.logintest.controller;

import com.minatoaqua.logintest.dao.RegisterResponse;
import com.minatoaqua.logintest.dao.UsersDao;
import com.minatoaqua.logintest.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: JLChen
 * @since: 2024-09-07 22:29
 * @description: 调用服务层，处理用户注册请求，并返回处理结果
 */


@RestController
@RequestMapping("/user-api")
public class UsersRegisterController {

    @Autowired
    public UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody UsersDao usersDao) {
        RegisterResponse result = usersService.registerUser(usersDao);

        if ("success".equals(result.getStatus())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
    }
}
