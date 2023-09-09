package com.sparta.myblogbackend.auth;


import com.sparta.myblogbackend.dto.PostRequestDto;
import com.sparta.myblogbackend.dto.PostResponseDto;
import com.sparta.myblogbackend.entity.Post;
import com.sparta.myblogbackend.jwt.JwtUtil;
import com.sparta.myblogbackend.service.PostService;
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
    public Post getPost(Long id) {
        return postService.getPost(id);
    }

    //선택한 게시글 수정
    @PutMapping("/posts/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @CookieValue(value = JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        return postService.updatePost(id, requestDto, tokenValue);
    }

    //선택한 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public Long deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

    //키워드 게시글 조회
    @GetMapping("/posts/content")
    public List<PostResponseDto> getPostByKeyword(String keyword){
        return  postService.getPostByKeyword(keyword);
    }


}
