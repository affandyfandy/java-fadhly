package com.week10.post.service;

import com.week10.post.entity.Post;
import com.week10.post.dto.PostSaveDTO;
import com.week10.post.dto.PostShowDTO;

import java.util.List;

public interface PostService {
    
    List<PostShowDTO> getAllPosts();
    PostShowDTO getPostById(Long id);
    Post createNewPost(PostSaveDTO postSave);
    Post updateExistingPost(Long id, PostSaveDTO postSave);
    void deletePost(Long id);
}
