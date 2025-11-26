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

@Valid
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllPosts(){
        return ResponseEntity.status(200).body(postService.getAllPosts());
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
        boolean isUpdated = postService.deletePost(id);
        if (!isUpdated){
            return ResponseEntity.status(400).body("post not deleted");
        }
        return ResponseEntity.status(200).body("post deleted");
    }



    //EndPoint1
    @GetMapping("/search/title")
    public ResponseEntity<?> searchPostsByWords(@RequestParam String keyword){

        return ResponseEntity.status(200).body(postService.searchByWords(keyword));

    }

    //EndPoint2 getPostsBetweenDates
    @GetMapping("/search/date")
    public ResponseEntity<?> getPostsBetweenDates(@RequestParam LocalDate start, @RequestParam LocalDate end){

        return ResponseEntity.status(200).body(postService.searchBetweenTwoDates(start, end));
    }

    //EndPoint5

    @GetMapping("/sorted/content-length")
    public ResponseEntity<?> getPostsSortedByLength(){
        return ResponseEntity.status(200).body(postService.getPostsSortedByLength());

    }

}
