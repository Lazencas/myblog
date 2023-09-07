package com.sparta.myblogbackend.service;

import com.sparta.myblogbackend.dto.PostRequestDto;
import com.sparta.myblogbackend.dto.PostResponseDto;
import com.sparta.myblogbackend.entity.Post;
import com.sparta.myblogbackend.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        //DB저장
        Post savePost = postRepository.save(post);

//Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }

    //# 모든 게시글 조회
    public List<PostResponseDto> getAllposts() {
        //DB조회
        return postRepository.findAll().stream().map(PostResponseDto::new).toList();
    }

    //# 선택 게시글 조회
    public Post getPost(Long id) {
        //DB조회
        return postRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.")
        );
    }

    //# 선택 게시글 수정
    @Transactional
    public Long updatePost(Long id, PostRequestDto requestDto) {
        //해당 메모가 DB에 존재하는지 확인
        Post post = findPost(id);
            //포스트 수정
        post.update(requestDto);

 return id;
    }

    //# 선택 게시글 삭제
    public Long deletePost(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Post post = findPost(id);
            //해당 메모 삭제
        postRepository.delete(post);
        return id;
    }


// 해당 메모가 DB에 존재하는지 확인
    private Post findPost(Long id){
      return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.")
        );
    }

}
