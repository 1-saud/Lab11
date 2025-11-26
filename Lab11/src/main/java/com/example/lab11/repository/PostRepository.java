package com.example.lab11.repository;

import com.example.lab11.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post , Integer> {


    Post findPostById(Integer id);

    boolean existsByUserId(Integer userId);

    boolean existsByCategoryId(Integer categoryId);

    // EndPoint1
    @Query("select p from Post p where p.title like %?1%")
    List<Post> findPostsByWords(String keyword);

    // EndPoint2
    @Query("select p from Post p where p.publish_date between ?1 and ?2")
    List<Post> findPostsByTwoDates(LocalDate start, LocalDate end);

    // EndPoint5
    @Query("select p from Post p order by length(p.content) desc")
    List<Post> findPostsOrderByLength();
}


