package com.example.lab11.service;

import com.example.lab11.model.Post;
import com.example.lab11.model.User;
import com.example.lab11.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public void addPost(Post post){
        postRepository.save(post);
    }

    public boolean updatePost(Integer id ,Post post ){

        Post oldPost = postRepository.findPostById(id);

        if (oldPost == null){
            return false;
        }

        oldPost.setCategory(post.getCategory());
        oldPost.setContent(post.getContent());
        oldPost.setPublish_date(post.getPublish_date());
        oldPost.setTitle(post.getTitle());
        oldPost.setId(post.getId());
        postRepository.save(oldPost);
        return true;
    }

    public boolean deletePost(Integer id ){
        Post post = postRepository.findPostById(id);
        if (post == null){
            return false;
        }

        postRepository.delete(post);
        return true;
    }

    public List<Post> searchByWords(String keyword){
        return postRepository.findPostsByWords(keyword);
    }

    public List<Post> searchBetweenTwoDates(LocalDate start , LocalDate end){
        return postRepository.findPostsByTwoDates(start, end);
    }

    //EndPoint5
    public List<Post> getPostsSortedByLength(){
        return postRepository.findPostsOrderByLength();
    }



}
