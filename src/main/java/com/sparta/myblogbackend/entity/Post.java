package com.sparta.myblogbackend.entity;

import com.sparta.myblogbackend.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;

@Entity
@Getter
@Setter
@Table(name="post")
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="title", nullable=false)
    private String title;
    @Column(name="username", nullable=false)
    private String username;
    @Column(name="content", nullable=false, length = 500)
    private String content;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private List<Comment> commentList = new ArrayList<>();

    public Post(PostRequestDto requestDto) {
        LocalDateTime now = now();
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
        this.title = requestDto.getTitle();
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
