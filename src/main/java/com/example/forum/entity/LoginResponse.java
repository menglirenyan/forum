package com.example.forum.entity;

import lombok.Data;
import lombok.Builder; // 新增注解

@Data
@Builder // 启用构建器模式
public class LoginResponse {
    private String status; // 状态（"success" / "error"）
    private String token; // 令牌（成功时返回）
    private String message;// 错误信息（失败时返回）
}