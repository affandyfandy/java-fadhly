package com.lecture16.assignment1_3.service.Impl;

import com.lecture16.assignment1_3.service.CommentService;
import com.lecture16.assignment1_3.repository.CommentRepository;
import com.lecture16.assignment1_3.entity.Comment;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

}
