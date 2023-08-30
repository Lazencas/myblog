package com.blog.myblog.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String username;
    private String title;
    private String contents;
    private String date;
    private String password;
    private String inputpassword;
}