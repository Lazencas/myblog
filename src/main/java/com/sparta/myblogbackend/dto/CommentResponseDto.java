package com.sparta.myblogbackend.dto;


import com.sparta.myblogbackend.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {
    Long id;
    private String username;
    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.username = comment.getUsername();
        this.content = comment.getContent();
        this.modifiedAt = comment.getModifiedAt();
        this.createdAt = comment.getCreatedAt();
    }

}
