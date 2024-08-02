package com.example.lecture16.assignment1_1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentShowDTO {
    private Long id;
    private String content;
}