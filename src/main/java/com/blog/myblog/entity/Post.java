package com.blog.myblog.entity;

import com.blog.myblog.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class Post {
    private Long id;
    private String username;
    private String title;
    private String contents;
    private String date;
    private String password;
    private String inputpw;

    public Post(PostRequestDto requestDto) {
        LocalDateTime now = LocalDateTime.now();

        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.date = String.valueOf(now.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")));
        this.password = requestDto.getPassword();
        this.inputpw = requestDto.getInputpassword();

    }

    public void update(PostRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }

    public void inputpw(PostRequestDto requestDto) {
        this.inputpw = requestDto.getInputpassword();
    }
}