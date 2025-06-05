package com.example.forum.entity;

import lombok.Data;

@Data // Lombok 注解，自动生成 get/set 方法
public class LoginRequest {
    private String username; // 用户名
    private String password; // 密码
}