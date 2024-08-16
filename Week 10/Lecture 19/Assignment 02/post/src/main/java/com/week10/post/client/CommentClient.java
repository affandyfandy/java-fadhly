package com.week10.post.client;

import com.week10.post.dto.CommentShowDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "commentholder", url = "http://localhost:8082/api/v2")
public interface CommentClient {

    @GetMapping("/comments/{id}")
    CommentShowDTO getCommentById(@PathVariable("id") Long id);
}
