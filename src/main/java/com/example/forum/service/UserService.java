package com.example.forum.service;

import com.example.forum.entity.User;
import com.example.forum.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // 加密依赖
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder; // 密码加密器

    // 构造方法注入依赖
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // 初始化加密器
    }

    // 注册方法：返回true=成功，false=用户名已存在
    public boolean register(User user) {
        // 1. 检查用户名是否已存在
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            return false;
        }

        // 2. 加密密码（必须！避免明文存储）
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        // 3. 保存到数据库
        userRepository.save(user);
        return true;
    }

    // 登录方法（返回 User 对象，null 表示失败）
    public User login(String username, String password) {
        // 1. 根据用户名查数据库
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null; // 用户不存在
        }
        // 2. 密码校验（明文 vs 加密密码）
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user; // 密码匹配，登录成功
        } else {
            return null; // 密码不匹配
        }
    }
}