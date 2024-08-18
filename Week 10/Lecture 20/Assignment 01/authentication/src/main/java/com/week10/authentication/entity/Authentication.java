package com.week10.authentication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Authentication {

    @Id
    private String apiKey;
}
