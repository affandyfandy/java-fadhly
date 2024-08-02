package com.lecture16.assignment1_3.entity;

import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.*;

@Setter
@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String content;
}
