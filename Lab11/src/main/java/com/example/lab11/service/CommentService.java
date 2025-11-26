package com.example.lab11.service;

import com.example.lab11.model.Comment;
import com.example.lab11.model.User;
import com.example.lab11.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public void addComment(Comment comment){
        commentRepository.save(comment);
    }

    public boolean updateComment(Integer id , Comment comment){
        Comment oldComment = commentRepository.findCommentById(id);
        if (oldComment == null){
            return false;
        }

        oldComment.setContent(comment.getContent());
        oldComment.setComment_date(comment.getComment_date());
        oldComment.setUser(comment.getUser());
        oldComment.setPost(comment.getPost());
        commentRepository.save(oldComment);
        return true;
    }

    public boolean deleteComment(Integer id){
        Comment comment = commentRepository.findCommentById(id);
        if (comment == null){
            return false;
        }
        commentRepository.delete(comment);
        return true;
    }

    // EndPoint3
    public Integer countComments(Integer postId){
        return commentRepository.countCommentsByPostId(postId);
    }

    //Endpoint4
    public List<User> getMostActiveUsers(){
        return commentRepository.findMostActiveUsers();
    }

    //EndPoint 8
    public List<Comment> searchComments(String keyword){
        return commentRepository.findByContentContaining(keyword);
    }


}

