package com.example.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "forum_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 帖子标题

    @Column(columnDefinition = "TEXT")
    private String content; // 帖子内容

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author; // 发帖用户

    @Column(nullable = false)
    private LocalDateTime createTime; // 发布时间

    // 构造方法、getter和setter（Lombok已自动生成）
}