package com.week10.post.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.week10.post.entity.Post;
import com.week10.post.dto.PostSaveDTO;
import com.week10.post.dto.PostShowDTO;
import com.week10.post.service.PostService;

import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/v2/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping(value = "/")
    public ResponseEntity<List<PostShowDTO>> getAllPosts() {
        List<PostShowDTO> posts = postService.getAllPosts();

        if (posts == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<PostShowDTO> getPostById(@PathVariable("id") Long id) {
        PostShowDTO post = postService.getPostById(id);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Post> createNewPost(@RequestBody PostSaveDTO postSave) {
        Post post = postService.createNewPost(postSave);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Post> updateExistingPost(@PathVariable("id") Long id, @RequestBody PostSaveDTO postSave) {
        Post post = postService.updateExistingPost(id, postSave);
        
        if(post == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteExistingPost(@PathVariable("id") Long id) {
        PostShowDTO post = postService.getPostById(id);

        if(post == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        postService.deletePost(id);
        return ResponseEntity.status(HttpStatus.OK).body("Post have been Deleted");
    }
}
