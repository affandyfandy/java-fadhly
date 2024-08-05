package com.example.lecture16.assignment1_1.client;

import com.example.lecture16.assignment1_1.entity.Post;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "jsonplaceholder", url = "https://jsonplaceholder.typicode.com/")
public interface JsonPlaceholderClient {
    @GetMapping("/posts/{postId}")
    Post getPostById(@PathVariable("postId") Long id);
}
