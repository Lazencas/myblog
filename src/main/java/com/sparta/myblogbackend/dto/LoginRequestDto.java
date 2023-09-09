package com.sparta.myblogbackend.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@Setter
@Getter
public class LoginRequestDto {
    private String username;
    private String password;
}