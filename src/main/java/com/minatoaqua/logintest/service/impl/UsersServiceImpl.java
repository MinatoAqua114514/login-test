package com.minatoaqua.logintest.service.impl;

import com.minatoaqua.logintest.dao.RegisterResponse;
import com.minatoaqua.logintest.dao.UsersDao;
import com.minatoaqua.logintest.mapper.UsersMapper;
import com.minatoaqua.logintest.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author: JLChen
 * @since: 2024-09-07 22:28
 * @description: 实现用户注册流程逻辑
 */


@Service
public class UsersServiceImpl implements UsersService {
    private static final String EMAIL_EXISTS = "邮箱已存在";
    private static final String USERNAME_EXISTS = "用户名已存在";
    private static final String REGISTER_SUCCESS = "注册成功";

    @Autowired
    private UsersMapper usersMapper;

    public RegisterResponse registerUser(UsersDao usersDao) {
        // 检查用户名和邮箱是否已经存在
        if (usersMapper.selectUserByUserName(usersDao.getUsername()) != null) {
            return new RegisterResponse("error", USERNAME_EXISTS, usersDao.getUsername(), usersDao.getEmail());
        }
        if (usersMapper.selectUserByEmail(usersDao.getEmail()) != null) {
            return new RegisterResponse("error", EMAIL_EXISTS, usersDao.getUsername(), usersDao.getEmail());
        }

        // 设置用户创建的时间
        usersDao.setCreated_at(LocalDateTime.now());

        // 保存用户的注册信息
        usersMapper.insertUser(usersDao);
        return new RegisterResponse("success", REGISTER_SUCCESS, usersDao.getUsername(), usersDao.getEmail());
    }
}