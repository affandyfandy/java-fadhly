package com.example.lecture16.assignment1_1.service;

import java.util.List;

import com.example.lecture16.assignment1_1.entity.Post;
import com.example.lecture16.assignment1_1.dto.PostShowDTO;

public interface PostService {
    List<PostShowDTO> getAllPostsUsingFeignClient();
    List<PostShowDTO> getAllPostsUsingRestTemplate();
    List<PostShowDTO> getAllPostsUsingWebClient();
    Post createPost(Post post);
}
