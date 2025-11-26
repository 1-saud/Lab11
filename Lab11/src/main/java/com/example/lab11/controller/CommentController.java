package com.example.lab11.controller;

import com.example.lab11.model.Comment;
import com.example.lab11.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Valid
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/blog/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllComments(){
        return ResponseEntity.status(200).body(commentService.getAllComments());

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
        Integer count = commentService.countComments(postId);
        return ResponseEntity.status(200).body(count);

    }

    //EndPoint4
    @GetMapping("/active-users")
    public ResponseEntity<?> getMostActiveUsers(){
        return ResponseEntity.status(200).body(commentService.getMostActiveUsers());


    }

    //Endpoint8
    @GetMapping("/search/content")
    public ResponseEntity<?> searchComments(@RequestParam String keyword){
        return ResponseEntity.status(200).body(commentService.searchComments(keyword));
    }




}
