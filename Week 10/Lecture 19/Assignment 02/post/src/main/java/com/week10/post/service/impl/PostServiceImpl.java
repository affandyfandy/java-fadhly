package com.week10.post.service.impl;

import org.springframework.stereotype.Service;

import com.week10.post.client.CommentClient;
import com.week10.post.entity.Post;
import com.week10.post.dto.PostSaveDTO;
import com.week10.post.dto.PostShowDTO;
import com.week10.post.dto.CommentShowDTO;
import com.week10.post.mapper.PostMapper;
import com.week10.post.service.PostService;
import com.week10.post.repository.PostRepository;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final CommentClient commentClient;
    
    @Override
    public List<PostShowDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(post -> {
            PostShowDTO postShowDTO = postMapper.toPostShowDTO(post);
            CommentShowDTO comment = commentClient.getCommentById(post.getCommentId());
            postShowDTO.setComment(comment);
            return postShowDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public PostShowDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if(post == null) {
            return null;
        }

        PostShowDTO postShow = postMapper.toPostShowDTO(post);
        CommentShowDTO comment = commentClient.getCommentById(post.getCommentId());
        postShow.setComment(comment);

        return postShow;
    }

    @Override
    public Post createNewPost(PostSaveDTO postSave) {
        Post post = postMapper.toPost(postSave);

        Post savedPost = postRepository.save(post);
        return savedPost;
    }

    @Override
    public Post updateExistingPost(Long id, PostSaveDTO postSave) {
        Post checkPost = postRepository.findById(id).orElse(null);

        if (checkPost == null) {
            return null;
        }
        Post post = postMapper.toPost(postSave);
        checkPost.setTitle(post.getTitle());
        checkPost.setBody(post.getBody());
        checkPost.setCommentId(post.getCommentId());

        Post updatedPost = postRepository.save(checkPost);
        return updatedPost;
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElse(null);

        postRepository.delete(post);
    }
}
