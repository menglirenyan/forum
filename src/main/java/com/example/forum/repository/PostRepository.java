package com.example.forum.repository;

import com.example.forum.entity.Post;
import com.example.forum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // 按用户查询帖子
    List<Post> findByAuthor(User author);

    // 按标题模糊查询
    List<Post> findByTitleContaining(String keyword);
}