package com.minatoaqua.logintest.dao;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author: JLChen
 * @since: 2024-09-07 20:59
 * @description: 实体类 ——Users表
 */

@Getter
@Setter
public class UsersDao {
    private Integer id;
    private String username;
    private String email;
    private String password_hash;
    private LocalDateTime created_at;
}
