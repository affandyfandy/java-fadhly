package com.week10.comment.service;

import com.week10.comment.entity.Comment;
import com.week10.comment.dto.CommentSaveDTO;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComments();
    Comment getCommentById(Long id);
    Comment createNewComment(CommentSaveDTO commentSave);
    Comment updateExistingComment(Long id, CommentSaveDTO commentSave);
    void deleteComment(Long id);
}
