package com.example.forum.controller;

import com.example.forum.entity.Post;
import com.example.forum.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 发布新帖子（需要登录认证）
    @PostMapping
    public ResponseEntity<Post> createPost(
            @RequestHeader("Authorization") String token, // 从请求头获取Token
            @RequestBody PostRequest postRequest) {
        // 解析Token获取用户名（实际需结合JWT或Session）
        String username = parseToken(token); // 简化处理，实际需实现Token解析

        Post createdPost = postService.createPost(
                postRequest.getTitle(),
                postRequest.getContent(),
                username);
        return ResponseEntity.created(null).body(createdPost);
    }

    // 获取所有帖子
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // 内部类：请求体模型
    static class PostRequest {
        private String title;
        private String content;

        // getter和setter
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    // 简化的Token解析（实际需使用JWT库）
    private String parseToken(String token) {
        // 示例：假设Token格式为"Bearer username"
        if (token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        throw new IllegalArgumentException("无效的Token");
    }
}