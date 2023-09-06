package com.sparta.myblogbackend.entity;

import com.sparta.myblogbackend.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@Getter
@Setter
@Table(name="post")
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="title", nullable=false)
    private String title;
    @Column(name="username", nullable=false)
    private String username;
    @Column(name="content", nullable=false, length = 500)
    private String content;
    @Column(name="createdAT", nullable=true)
    private LocalDateTime cteatedAt;


    public Post(PostRequestDto requestDto) {
        LocalDateTime now = now();
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
        this.title = requestDto.getTitle();
        this.cteatedAt = now();

    }

    public void update(PostRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
