package com.example.forum.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data // Lombok自动生成get/set
@Entity // 标记为JPA实体，对应数据库表
public class User {
    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增ID
    private Long id;
    private String username; // 用户名（唯一）
    private String password; // 密码（建议加密存储）
}