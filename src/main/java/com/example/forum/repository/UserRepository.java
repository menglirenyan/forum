package com.example.forum.repository;

import com.example.forum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 自定义查询：根据用户名查找用户（用于注册时查重）
    User findByUsername(String username);
}