package com.example.lab11.controller;

import com.example.lab11.model.Comment;
import com.example.lab11.model.User;
import com.example.lab11.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Valid
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/blog/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllComments(){
        List<Comment> comments = commentService.getAllComments();
        if (comments.isEmpty()){
            return ResponseEntity.status(200).body("No comments found");
         }
        return ResponseEntity.status(200).body(comments);


    }



    @PostMapping("/add")
    public ResponseEntity<?> addComment(@Valid @RequestBody Comment comment , Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
              return ResponseEntity.status(400).body(message);

        }
        commentService.addComment(comment);
        return ResponseEntity.status(200).body("comment is added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Integer id , @Valid @RequestBody Comment comment , Errors errors){
        if (errors.hasErrors()){

            String message = errors.getFieldError().getDefaultMessage();
             return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = commentService.updateComment(id, comment);
        if (!isUpdated){
            return ResponseEntity.status(400).body("comment not updated");
        }

        return ResponseEntity.status(200).body("comment updated");
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer id){
        boolean isDeleted = commentService.deleteComment(id);

        if (!isDeleted){
            return ResponseEntity.status(400).body("comment not deleted");
        }

            return ResponseEntity.status(200).body("comment deleted");
    }


    //EndPoint3
    @GetMapping("/count/{postId}")
    public ResponseEntity<?> countComments(@PathVariable Integer postId){
        if (postId == null || postId <= 0){
            return ResponseEntity.status(400).body("postId must be a positive number");

        }

        Integer count = commentService.countComments(postId);
        return ResponseEntity.status(200).body(count);
    }

    //EndPoint4
    @GetMapping("/active-users")
    public ResponseEntity<?> getMostActiveUsers(){

        List<User> users = commentService.getMostActiveUsers();

        if (users.isEmpty()){
            return ResponseEntity.status(200).body("No active users found");
        }

        return ResponseEntity.status(200).body(users);
    }

    //Endpoint8
    @GetMapping("/search/content")
    public ResponseEntity<?> searchComments(@RequestParam String keyword){
        if (keyword == null || keyword.isBlank()){
            return ResponseEntity.status(400).body("keyword shouldnt be empty");
        }

        if (keyword.length() < 3){
            return ResponseEntity.status(400).body("lenght of keyword should be 3 or more");
        }
        List<Comment> comments = commentService.searchComments(keyword);

         if (comments.isEmpty()){

             return ResponseEntity.status(200).body("No comments found ");
        }

          return ResponseEntity.status(200).body(comments);
    }




}
