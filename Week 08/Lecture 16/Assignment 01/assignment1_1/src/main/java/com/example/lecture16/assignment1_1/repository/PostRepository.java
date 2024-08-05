package com.example.lecture16.assignment1_1.repository;

import com.example.lecture16.assignment1_1.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}