package com.example.lecture16.assignment1_1.service.Impl;

import com.example.lecture16.assignment1_1.entity.Post;
import com.example.lecture16.assignment1_1.service.PostService;
import com.example.lecture16.assignment1_1.service.CommentService;
import com.example.lecture16.assignment1_1.repository.PostRepository;
import com.example.lecture16.assignment1_1.dto.PostShowDTO;
import com.example.lecture16.assignment1_1.dto.CommentShowDTO;
import com.example.lecture16.assignment1_1.mapper.PostMapper;
import com.example.lecture16.assignment1_1.client.CommentClient;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final CommentClient commentClient;
    private final CommentService commentService;

    @Override
    public List<PostShowDTO> getAllPostsUsingFeignClient() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(post -> {
                    PostShowDTO postDTO = postMapper.toPostShowDTO(post);
                    List<CommentShowDTO> comments = postMapper.mapCommentsUsingFeignClient(post.getCommentId(), commentClient);
                    postDTO.setComments(comments);
                    return postDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<PostShowDTO> getAllPostsUsingRestTemplate() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(post -> {
                    PostShowDTO postDTO = postMapper.toPostShowDTO(post);
                    List<CommentShowDTO> comments = postMapper.mapCommentsUsingRestTemplate(post.getCommentId(), commentService);
                    postDTO.setComments(comments);
                    return postDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<PostShowDTO> getAllPostsUsingWebClient() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(post -> {
                    PostShowDTO postDTO = postMapper.toPostShowDTO(post);
                    List<CommentShowDTO> comments = postMapper.mapCommentsUsingWebclient(post.getCommentId(), commentService);
                    postDTO.setComments(comments);
                    return postDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }
}
