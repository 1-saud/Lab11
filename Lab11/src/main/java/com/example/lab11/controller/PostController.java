package com.example.lab11.controller;

import com.example.lab11.model.Post;
import com.example.lab11.repository.PostRepository;
import com.example.lab11.service.PostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Valid
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllPosts(){
        List<Post> posts = postService.getPostsSortedByLength();

        if (posts.isEmpty()){
            return ResponseEntity.status(200).body("No posts found");
        }

        return ResponseEntity.status(200).body(posts);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody @Valid Post post , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        postService.addPost(post);
        return ResponseEntity.status(200).body("post added");

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Integer id ,@RequestBody @Valid Post post , Errors errors ){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = postService.updatePost(id , post);
        if (!isUpdated){
            return ResponseEntity.status(400).body("post not updated");
        }
        return ResponseEntity.status(200).body("updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Integer id){
        boolean isDeleted = postService.deletePost(id);
        if (!isDeleted){
            return ResponseEntity.status(400).body("post not deleted - the post may have comments ");
        }
        return ResponseEntity.status(200).body("post deleted");
    }



    //EndPoint1
    @GetMapping("/search/title")
    public ResponseEntity<?> searchPostsByWords(@RequestParam String keyword){
        if (keyword == null){
            return ResponseEntity.status(400).body("keyword shouldnt be empty");
        }

        if (keyword.length() < 3){
            return ResponseEntity.status(400).body("keyword should be at least 3 characters");
        }

        return ResponseEntity.status(200).body(postService.searchByWords(keyword));
    }


    //EndPoint2 getPostsBetweenDates
    @GetMapping("/search/date")
    public ResponseEntity<?> getPostsBetweenDates(@RequestParam LocalDate start, @RequestParam LocalDate end){

        if (start == null || end == null){
            return ResponseEntity.status(400).body("start and end dates shouldnt be null");
        }

        if (start.isAfter(end)){
            return ResponseEntity.status(400).body("start date should be before end date");
        }

        return ResponseEntity.status(200).body(postService.searchBetweenTwoDates(start, end));
    }
    //EndPoint5

    @GetMapping("/sorted/content-length")
    public ResponseEntity<?> getPostsSortedByLength(){

        List<Post> posts = postService.getPostsSortedByLength();

        if (posts.isEmpty()){
            return ResponseEntity.status(200).body("No posts found");
        }

        return ResponseEntity.status(200).body(posts);
    }

}
