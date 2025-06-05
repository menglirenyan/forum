package com.example.forum.controller;

import com.example.forum.entity.User;
import com.example.forum.service.UserService;
import com.example.forum.entity.LoginRequest;
import com.example.forum.entity.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // 接口统一前缀
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        // 调用 Service 校验用户
        User user = userService.login(request.getUsername(), request.getPassword());
        if (user != null) {
            // 登录成功：生成 Token（示例用时间戳，实际应替换为 JWT）
            String token = "Bearer_" + System.currentTimeMillis();
            return ResponseEntity.ok(LoginResponse.builder()
                    .status("success")
                    .token(token)
                    .build());
        } else {
            // 登录失败：统一提示（避免暴露具体原因）
            return ResponseEntity.badRequest().body(LoginResponse.builder()
                    .status("error")
                    .message("用户不存在或密码错误")
                    .build());
        }
    }

    private final UserService userService;

    // 构造方法注入Service
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 注册接口：POST /api/register
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        boolean isSuccess = userService.register(user);
        if (isSuccess) {
            return ResponseEntity.ok("注册成功");
        } else {
            return ResponseEntity.badRequest().body("用户名已存在");
        }
    }
}