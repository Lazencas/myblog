package com.sparta.myblogbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentRequestDto {
    Long id;
    private String username;
    private String content;
}
