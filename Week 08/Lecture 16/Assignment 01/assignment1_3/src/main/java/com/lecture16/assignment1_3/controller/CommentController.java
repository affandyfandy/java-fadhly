package com.lecture16.assignment1_3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lecture16.assignment1_3.entity.Comment;
import com.lecture16.assignment1_3.service.CommentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") Long id) {
        Comment com = commentService.getCommentById(id);
        if(com == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(com);
    }
}
