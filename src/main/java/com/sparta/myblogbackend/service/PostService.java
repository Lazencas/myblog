package com.sparta.myblogbackend.service;

import com.sparta.myblogbackend.dto.PostRequestDto;
import com.sparta.myblogbackend.dto.PostResponseDto;
import com.sparta.myblogbackend.entity.Post;
import com.sparta.myblogbackend.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    //# 게시글 작성
    public PostResponseDto createPost(PostRequestDto requestDto) {
        //RequestDto -> Entity
        Post post = new Post(requestDto);

        //Memo Max ID Check

        //DB저장


//Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }

    //# 모든 게시글 조회
    public String getAllposts() {
        return "all get";
    }

    //# 선택 게시글 조회
    public String getPost() {
        return "choice post";
    }

    //# 선택 게시글 수정
    public Long updatePost(Long id, PostRequestDto requestDto) {

        //해당 메모가 DB에 존재하는지 확인

            //해당 메모 가져오기


            //포스트 수정
 return id;
    }

    //# 선택 게시글 삭제
    public Long deletePost(Long id) {
        // 해당 메모가 DB에 존재하는지 확인


            //해당 메모 삭제

        return id;
    }
}
