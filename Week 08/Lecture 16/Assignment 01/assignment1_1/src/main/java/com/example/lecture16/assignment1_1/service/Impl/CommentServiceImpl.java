package com.example.lecture16.assignment1_1.service.Impl;

import com.example.lecture16.assignment1_1.service.CommentService;
import com.example.lecture16.assignment1_1.dto.CommentShowDTO;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;

    @Override
    public CommentShowDTO getCommentByIdUsingRestTemplate(Long id) {
        return restTemplate.getForObject("http://localhost:8081/api/v1/comments/" + id, CommentShowDTO.class);
    }

    @Override
    public CommentShowDTO getCommentByIdUsingWebclient(Long id) {
        WebClient webClient = webClientBuilder.baseUrl("http://localhost:8081/api/v1").build();
        return webClient.get()
                .uri("/comments/" + id)
                .retrieve()
                .bodyToMono(CommentShowDTO.class)
                .block();
    }
}