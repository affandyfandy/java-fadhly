package com.week10.comment.service.impl;

import java.util.List;

import com.week10.comment.dto.CommentSaveDTO;
import com.week10.comment.entity.Comment;
import com.week10.comment.mapper.CommentMapper;
import com.week10.comment.service.CommentService;
import com.week10.comment.repository.CommentRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public List<Comment> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments;
    }

    @Override
    public Comment getCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        return comment;
    }

    @Override
    public Comment createNewComment(CommentSaveDTO commentSave) {
        Comment comment = commentMapper.toComment(commentSave);

        Comment savedComment = commentRepository.save(comment);
        return savedComment;
    }

    @Override
    public Comment updateExistingComment(Long id, CommentSaveDTO commentSave) {
        Comment checkComment = getCommentById(id);

        if(checkComment == null) {
            return null;
        }
        Comment comment = commentMapper.toComment(commentSave);
        checkComment.setContent(comment.getContent());

        Comment updatedComment = commentRepository.save(checkComment);
        return updatedComment;
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = getCommentById(id);

        commentRepository.delete(comment);
    }
}
