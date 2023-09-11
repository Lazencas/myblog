package com.sparta.myblogbackend.controller;


import com.sparta.myblogbackend.dto.*;
import com.sparta.myblogbackend.entity.Post;
import com.sparta.myblogbackend.jwt.JwtUtil;
import com.sparta.myblogbackend.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //게시글 작성
    @PostMapping("/writepost")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, @CookieValue(value = JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        return postService.createPost(requestDto, tokenValue);
    }

    //전체 게시글 목록 조회
    @GetMapping("/posts")
    public List<PostResponseDto> getAllposts() {
        return postService.getAllposts();
    }

    //선택한 게시글 조회
    @GetMapping("/posts/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    //선택한 게시글 수정
    @PutMapping("/posts/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @CookieValue(value = JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        return postService.updatePost(id, requestDto, tokenValue);
    }

    //선택한 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<ResponseMethodDto> deletePost(@PathVariable Long id, @CookieValue(value = JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        return postService.deletePost(id, tokenValue);
    }

    //키워드 게시글 조회
    @GetMapping("/posts/content")
    public List<PostResponseDto> getPostByKeyword(String keyword){
        return  postService.getPostByKeyword(keyword);
    }

    //댓글생성
    @PostMapping("/posts/{id}/comment-write")
    public CommentResponseDto createComment(@PathVariable Long id,@RequestBody CommentRequestDto requestDto,  @CookieValue(value = JwtUtil.AUTHORIZATION_HEADER) String tokenValue){
        return postService.createComment(id,requestDto,tokenValue);
    }

    //댓글수정
    @PutMapping("/posts/comment-update/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto,  @CookieValue(value = JwtUtil.AUTHORIZATION_HEADER) String tokenValue){
        return postService.updateComment(commentId,requestDto,tokenValue);
    }

    //댓글삭제
    @DeleteMapping("/posts/comment-delete/{commentId}")
    public ResponseEntity<ResponseMethodDto> deleteComment(@PathVariable Long commentId, @CookieValue(value = JwtUtil.AUTHORIZATION_HEADER) String tokenValue){
        return postService.deleteComment(commentId,tokenValue);
    }



}
