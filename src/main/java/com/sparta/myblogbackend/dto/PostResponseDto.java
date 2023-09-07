package com.sparta.myblogbackend.dto;
import com.sparta.myblogbackend.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private long id;
    private String title;
    private String username;
    private String content;
    private LocalDateTime cteatedAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post){
this.id = post.getId();
this.title = post.getTitle();
this.username = post.getUsername();
this.content = post.getContent();
this.cteatedAt= post.getCreatedAt();
this.modifiedAt = post.getModifiedAt();
    }

}
