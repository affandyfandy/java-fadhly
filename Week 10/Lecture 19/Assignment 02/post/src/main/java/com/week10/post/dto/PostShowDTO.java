package com.week10.post.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostShowDTO {
    private Long id;
    private String title;
    private String body;
    private CommentShowDTO comment;
}
