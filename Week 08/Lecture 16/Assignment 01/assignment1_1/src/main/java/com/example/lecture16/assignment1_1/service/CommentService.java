package com.example.lecture16.assignment1_1.service;

import com.example.lecture16.assignment1_1.dto.CommentShowDTO;

public interface CommentService {
    CommentShowDTO getCommentByIdUsingRestTemplate(Long id);
    CommentShowDTO getCommentByIdUsingWebclient(Long id);
}
