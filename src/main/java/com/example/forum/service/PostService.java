package com.example.forum.service;

import com.example.forum.entity.Post;
import com.example.forum.entity.User;
import com.example.forum.repository.PostRepository;
import com.example.forum.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Post createPost(String title, String content, String username) {
        // 查找当前登录用户
        User author = userRepository.findByUsername(username);
        if (author == null) {
            throw new IllegalArgumentException("用户不存在");
        }

        // 创建帖子
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);
        post.setCreateTime(LocalDateTime.now());

        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPostsByAuthor(String username) {
        User author = userRepository.findByUsername(username);
        return postRepository.findByAuthor(author);
    }
}