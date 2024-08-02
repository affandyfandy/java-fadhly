package com.example.lecture16.assignment1_1.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.example.lecture16.assignment1_1.service.PostService;
import com.example.lecture16.assignment1_1.entity.Post;
import com.example.lecture16.assignment1_1.dto.CommentShowDTO;
import com.example.lecture16.assignment1_1.dto.PostShowDTO;
import com.example.lecture16.assignment1_1.client.CommentClient;

import lombok.AllArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    private final CommentClient commentClient;

    @GetMapping(value = "/feign-client")
    public ResponseEntity<List<PostShowDTO>> getAllPostsUsingFeignClient() {
        List<PostShowDTO> posts = postService.getAllPostsUsingFeignClient();
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posts);
    }

    @GetMapping(value = "/rest-template")
    public ResponseEntity<List<PostShowDTO>> getAllPostsUsingRestTemplate() {
        List<PostShowDTO> posts = postService.getAllPostsUsingRestTemplate();
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posts);
    }

    @GetMapping(value = "/web-client")
    public ResponseEntity<List<PostShowDTO>> getAllPostsUsingWebClient() {
        List<PostShowDTO> posts = postService.getAllPostsUsingWebClient();
        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posts);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CommentShowDTO> getCommentById(@PathVariable("id") Long id) {
        CommentShowDTO com = commentClient.getCommentById(id);
        if(com == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(com);
    }

    @PostMapping
    public Post createNewPost(@RequestBody Post post) {
        return postService.createPost(post);
    }
}
