create database if not exists aqua_web_db;

use aqua_web_db;

-- 创建用户信息表
CREATE TABLE users
(
    id                  INT AUTO_INCREMENT PRIMARY KEY,                                                             -- 主键，自增用户ID
    username            VARCHAR(50)  NOT NULL UNIQUE,                                                               -- 用户名，唯一
    email               VARCHAR(100) NOT NULL UNIQUE,                                                               -- 邮箱，唯一
    password_hash       VARCHAR(255) NOT NULL,                                                                      -- 密码哈希值
    profile_picture_url VARCHAR(255),                                                                               -- 头像图片的URL
    status              ENUM ('active', 'inactive', 'banned') DEFAULT 'inactive',                                   -- 用户状态
    created_at          TIMESTAMP                             DEFAULT CURRENT_TIMESTAMP,                            -- 账号创建时间
    updated_at          TIMESTAMP                             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 更新时间
);
/*
	password_hash 使用了 VARCHAR(255)，
	确保存储足够长度的加密密码哈希值（如使用 bcrypt、argon2 等算法）。
*/


-- 创建用户角色表
CREATE TABLE roles
(
    id          INT AUTO_INCREMENT PRIMARY KEY, -- 主键，自增角色ID
    role_name   VARCHAR(50) NOT NULL UNIQUE,    -- 角色名称，唯一
    description VARCHAR(255)                    -- 角色描述
);


-- 创建用户角色关联表
CREATE TABLE user_roles
(
    user_id     INT NOT NULL,                                      -- 用户ID，外键
    role_id     INT NOT NULL,                                      -- 角色ID，外键
    assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,               -- 角色分配时间
    PRIMARY KEY (user_id, role_id),                                -- 联合主键，确保同一个用户不能重复分配相同的角色
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE, -- 用户ID外键，关联users表
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE  -- 角色ID外键，关联roles表
);
/*
	联合主键: (user_id, role_id) 确保每个用户在 user_roles 表中对同一个角色只能存在一条记录，防止重	复分配相同角色。
	外键约束: 确保数据的完整性，删除 users 或 roles 表中的记录时，相关联的 user_roles 记录会自动删除,
	避免数据孤立或不一致。
*/


-- 用户登录日志表
CREATE TABLE user_login_logs
(
    id          INT AUTO_INCREMENT PRIMARY KEY,                   -- 日志记录的唯一ID
    user_id     INT NOT NULL,                                     -- 用户ID，外键
    login_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,              -- 用户登录时间
    ip_address  VARCHAR(45),                                      -- 用户登录时的IP地址（IPv4或IPv6）
    device_info VARCHAR(255),                                     -- 登录设备的信息（如浏览器、操作系统）
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE -- 用户ID外键，关联users表
);


-- 用户活动表
CREATE TABLE user_activity
(
    id            INT AUTO_INCREMENT PRIMARY KEY,                              -- 活动记录的唯一ID
    user_id       INT                                                NOT NULL, -- 用户ID，外键
    activity_type ENUM ('comment', 'like', 'share', 'post', 'other') NOT NULL, -- 活动类型
    activity_data TEXT,                                                        -- 具体的活动数据，如评论内容、点赞对象等
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                         -- 活动发生时间
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE              -- 用户ID外键，关联users表
);