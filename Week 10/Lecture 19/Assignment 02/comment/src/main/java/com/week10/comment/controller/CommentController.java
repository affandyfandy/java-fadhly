package com.week10.comment.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.week10.comment.dto.CommentSaveDTO;
import com.week10.comment.entity.Comment;
import com.week10.comment.service.CommentService;

import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/v2/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();

        if(comments == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        Comment comment = commentService.getCommentById(id);
        
        if (comment == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Comment> createNewComment(@RequestBody CommentSaveDTO commentSave) {
        Comment comment = commentService.createNewComment(commentSave);

        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Comment> updateExistingComment(@PathVariable("id") Long id, @RequestBody CommentSaveDTO commentSave) {
        Comment comment = commentService.updateExistingComment(id, commentSave);

        if(comment == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteExistingComment(@PathVariable("id") Long id) {
        Comment comment = commentService.getCommentById(id);

        if(comment == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        commentService.deleteComment(id);
        return ResponseEntity.status(HttpStatus.OK).body("Comment have been deleted");
    }
}
