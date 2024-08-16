package com.week10.post.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.week10.post.dto.PostSaveDTO;
import com.week10.post.dto.PostShowDTO;
import com.week10.post.entity.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    PostSaveDTO toPostSaveDTO(Post post);

    @Mapping(target = "id", ignore = true)
    Post toPost(PostSaveDTO postSaveDTO);

    @Mapping(target = "comment", ignore = true)
    PostShowDTO toPostShowDTO(Post post);

    @Mapping(target = "commentId", ignore = true)
    Post toPost(PostShowDTO postShowDTO);
}