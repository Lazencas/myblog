package com.sparta.myblogbackend.dto;
import com.sparta.myblogbackend.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Getter
public class PostResponseDto {
    private long id;
    private String title;
    private String username;
    private String content;
    private LocalDateTime cteatedAt;
    private LocalDateTime modifiedAt;

    private List<CommentResponseDto> commentResponseDtoList;

    public PostResponseDto(Post post){
this.id = post.getId();
this.title = post.getTitle();
this.username = post.getUsername();
this.content = post.getContent();
this.cteatedAt= post.getCreatedAt();
this.modifiedAt = post.getModifiedAt();
this.commentResponseDtoList = post.getCommentList().stream().map(CommentResponseDto::new)
        .sorted(Comparator.comparing(CommentResponseDto::getModifiedAt).reversed())
        .toList();
    }

}
