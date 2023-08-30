package com.blog.myblog.controller;

import com.blog.myblog.dto.PostRequestDto;
import com.blog.myblog.dto.PostResponseDto;
import com.blog.myblog.entity.Post;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PostController {
    private final Map<Long, Post> postList = new HashMap<>();

    //게시글 작성
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto){
        // RequestDto -> Entity
        Post post = new Post(requestDto);

        // Memo Max  ID Check
        Long maxId = postList.size() > 0 ? Collections.max(postList.keySet()) + 1 : 1;
        post.setId(maxId);

        // DB 저장
        postList.put(post.getId(), post);


       // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }
//게시글 목록 조회
    @GetMapping("/post")
    public List<PostResponseDto> getPost(){
        // Map To List

       List<PostResponseDto> responseList = new ArrayList<PostResponseDto>(responseList=postList.values().stream()
               .map(PostResponseDto::new).toList());
                Collections.reverse(responseList);

        return responseList;
    }

    //게시글 선택 조회
    @GetMapping("post/{id}")
    public PostResponseDto getdetailPost(@PathVariable Long id){
        if(postList.containsKey(id)){
            //해당 게시글 가져오기
            Post post = postList.get(id);
            PostResponseDto detail = new PostResponseDto(post);
            return detail;

        }else{
            throw new IllegalArgumentException("해당 게시글은 존재하지 않습니다.");
        }

    }
    //게시글 수정
    @PutMapping("/post/{id}")
    public String updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        // 해당 메모가 DB에 존재하는지 확인
        if(postList.containsKey(id)){
            //해당 게시글 가져오기
            Post post = postList.get(id);
            post.inputpw(requestDto);
            if(post.getPassword().equals(post.getInputpw())){
                //post 수정
                post.update(requestDto);
                return "Success";
            }else{
                return "실패! 비밀번호가 올바르지 않습니다.";
            }

        }else{
            throw new IllegalArgumentException("해당 게시글은 존재하지 않습니다.");
        }

    }

    @DeleteMapping("/post/{id}")
    public  String deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        //해당 게시글이 DB에 존재하는지 확인
        if(postList.containsKey(id)){
            Post post = postList.get(id);
            post.inputpw(requestDto);
            if(post.getPassword().equals(post.getInputpw())){
                //해상 게시글 삭제하기
                postList.remove(id);
                return "Success";
            }else{
                return "기존:"+post.getPassword()+"입력:"+post.getInputpw();
                //return "실패! 비밀번호가 올바르지 않습니다.";
            }

        }else{
            throw new IllegalArgumentException("해당 게시글은 존재하지 않습니다.");
        }

    }






}
