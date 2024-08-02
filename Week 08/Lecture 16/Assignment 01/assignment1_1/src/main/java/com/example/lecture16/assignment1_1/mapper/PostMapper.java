package com.example.lecture16.assignment1_1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.lecture16.assignment1_1.dto.CommentShowDTO;
import com.example.lecture16.assignment1_1.client.CommentClient;
import com.example.lecture16.assignment1_1.service.CommentService;
import com.example.lecture16.assignment1_1.dto.PostShowDTO;
import com.example.lecture16.assignment1_1.entity.Post;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "comments", ignore = true)
    PostShowDTO toPostShowDTO(Post post);

    @Mapping(target = "commentId", ignore = true)
    Post toPost(PostShowDTO postShowDTO);

    default List<CommentShowDTO> mapCommentsUsingFeignClient(List<Long> commentIds, CommentClient commentClient) {
        return commentIds.stream()
                .map(commentClient::getCommentById)
                .collect(Collectors.toList());
    }

    default List<CommentShowDTO> mapCommentsUsingRestTemplate(List<Long> commentIds, CommentService commentService) {
        return commentIds.stream()
                .map(commentService::getCommentByIdUsingRestTemplate)
                .collect(Collectors.toList());
    }

    default List<CommentShowDTO> mapCommentsUsingWebclient(List<Long> commentIds, CommentService commentService) {
        return commentIds.stream()
                .map(commentService::getCommentByIdUsingWebclient)
                .collect(Collectors.toList());
    }
}