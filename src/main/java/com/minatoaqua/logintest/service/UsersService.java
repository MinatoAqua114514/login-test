package com.minatoaqua.logintest.service;

import com.minatoaqua.logintest.dao.UsersDao;
import org.springframework.stereotype.Service;

/**
 * @author: JLChen
 * @since: 2024-09-07 22:28
 * @description: 定义用户注册服务
 */


@Service
public interface UsersService {
    String registerUser(UsersDao usersDao);
}
