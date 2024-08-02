package com.example.lecture16.assignment1_1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostShowDTO {
    private Long id;
    private String title;
    private String body;
    private List<CommentShowDTO> comments;
}
