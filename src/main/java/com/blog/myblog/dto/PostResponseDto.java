package com.blog.myblog.dto;

import com.blog.myblog.entity.Post;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long id;
    private String username;
    private String title;
    private String contents;
    private String date;
    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.username = post.getUsername();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.date = post.getDate();

    }


}