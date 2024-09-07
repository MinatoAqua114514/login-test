package com.minatoaqua.logintest.mapper;

import com.minatoaqua.logintest.dao.UsersDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: JLChen
 * @since: 2024-09-07 22:27
 * @description: 映射数据库操作, 通过用户名/邮箱查询用户，插入用户
 */


@Mapper
public interface UsersMapper {
    UsersDao selectUserByUserName(String username);

    UsersDao selectUserByEmail(String email);

    void insertUser(UsersDao usersDao);
}
