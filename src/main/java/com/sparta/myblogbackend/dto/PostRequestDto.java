package com.sparta.myblogbackend.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostRequestDto {
    private long id;
    private String title;
    private String username;
    private String content;
    private LocalDateTime cteatedAt;


}
