package com.example.lab11.repository;

import com.example.lab11.model.Comment;
import com.example.lab11.model.User;
import jakarta.websocket.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment findCommentById(Integer id);

    // EndPoint3

    @Query("select count(c) from Comment c where c.post.id = ?1")
    Integer countCommentsByPostId(Integer postId);

    // Endpoint4

    @Query("select c.user from Comment c group by c.user order by count(c) desc")
    List<User> findMostActiveUsers();

    // EndPoint8 lastt last
    List<Comment> findByContentContaining(String keyword);



}
