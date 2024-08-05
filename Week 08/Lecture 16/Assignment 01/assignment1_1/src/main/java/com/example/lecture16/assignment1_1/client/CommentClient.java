package com.example.lecture16.assignment1_1.client;

import com.example.lecture16.assignment1_1.dto.CommentShowDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "commentholder", url = "http://localhost:8081/api/v1")
public interface CommentClient {
    @GetMapping("/comments/{id}")
    CommentShowDTO getCommentById(@PathVariable("id") Long id);
}
