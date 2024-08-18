package com.week10.comment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.week10.comment.dto.CommentSaveDTO;
import com.week10.comment.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentSaveDTO toCommentSaveDTO(Comment comment);

    @Mapping(target = "id", ignore = true)
    Comment toComment(CommentSaveDTO commentSaveDTO);
}
