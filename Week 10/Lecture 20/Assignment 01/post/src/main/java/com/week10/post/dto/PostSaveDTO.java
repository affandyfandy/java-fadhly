package com.week10.post.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostSaveDTO {
    private String body;
    private String title;
    private Long CommentId;
}
