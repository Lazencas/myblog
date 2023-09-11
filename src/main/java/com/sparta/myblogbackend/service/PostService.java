package com.sparta.myblogbackend.service;

import com.sparta.myblogbackend.dto.*;
import com.sparta.myblogbackend.entity.Comment;
import com.sparta.myblogbackend.entity.Post;
import com.sparta.myblogbackend.jwt.JwtUtil;
import com.sparta.myblogbackend.repository.CommentRepository;
import com.sparta.myblogbackend.repository.PostRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;
    private final ResponseMethodDto responseMethodDto;


    //# 게시글 작성
    public PostResponseDto createPost(PostRequestDto requestDto,String tokenValue) {
        //토큰 받아오기
        String token = jwtUtil.substringToken(tokenValue);
        //토큰 검증
        if(jwtUtil.validateToken(token)){
            //RequestDto -> Entity
            Post post = new Post(requestDto);
            //DB저장
            Post savePost = postRepository.save(post);
            //Entity -> ResponseDto
            PostResponseDto postResponseDto = new PostResponseDto(post);
            return postResponseDto;
        }else{
            throw new IllegalArgumentException("토큰 에러 : 유효한 사용자가 아닙니다.");
        }
    }

//키워드로 조회
    public List<PostResponseDto> getPostByKeyword(String keyword) {
        return postRepository.findAllByContentContainsOrderByModifiedAtDesc(keyword).stream().map(PostResponseDto::new).toList();
    }


    //# 모든 게시글 조회

    public List<PostResponseDto> getAllposts() {
        //DB조회
        return postRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).toList();
    }
    //# 선택 게시글 조회

    public PostResponseDto getPost(Long id) {
        //DB조회
        Post post = findPost(id);
        return new PostResponseDto(post);
    }

    //# 선택 게시글 수정
    @Transactional
    public Post updatePost(Long id, PostRequestDto requestDto, String tokenValue) {
        //해당 메모가 DB에 존재하는지 확인
        Post post = findPost(id);
        //토큰값을 가져와서 유효한지 검사하고, 해당 토큰의 사용자 정보를가져옴
        String token = jwtUtil.substringToken(tokenValue);
        if(!jwtUtil.validateToken(token)){
            throw new IllegalArgumentException("Token Error");
        }
        Claims info = jwtUtil.getUserInfoFromToken(token);
        //토큰에서 사용자 권한 가져와서 어드민인지 체크하기
        String auth = (String)info.get(JwtUtil.AUTHORIZATION_KEY);
        System.out.println("auth: "+auth);
        //어드민이면 그냥 수정하고 리턴
        if(auth.equals("ADMIN")){
            post.update(requestDto);
            return post;
        }
        //토큰에서 사용자 이름 가져오기
        String username = info.getSubject();
        //토큰의 사용자정보의 이름과 게시글의 사용자이름이 같은지 체크(본인이 쓴 글인지 확인)
        if(post.getUsername().equals(username)){
            //포스트 수정
            post.update(requestDto);
            return post;
        }else{
            throw new IllegalArgumentException("게시글을 작성한 사용자가 아닙니다.");
        }
    }
    //# 선택 게시글 삭제
    public ResponseEntity<ResponseMethodDto> deletePost(Long id, String tokenValue) {
        // 해당 메모가 DB에 존재하는지 확인
        Post post = findPost(id);
        //쿠키에서 토큰 값을 가져와서 스트링 변수 token에 넣음
        String token = jwtUtil.substringToken(tokenValue);
        //토큰의 유효성 검증
        if(!jwtUtil.validateToken(token)){
            throw new IllegalArgumentException("Token Error");
        }
        Claims info = jwtUtil.getUserInfoFromToken(token);
        //토큰에서 사용자 권한 가져와서 어드민인지 체크하기
        String auth = (String)info.get(JwtUtil.AUTHORIZATION_KEY);
        System.out.println("auth: "+auth);
        //어드민이면 그냥 수정하고 리턴
        if(auth.equals("ADMIN")){
            postRepository.delete(post);
            return responseMethodDto.DeleteSuccessStatus();
        }
        //토큰에서 사용자 이름 가져오기
        String username = info.getSubject();
        //토큰의 사용자정보의 이름과 게시글의 사용자이름이 같은지 체크(본인이 쓴 글인지 확인)
        if(post.getUsername().equals(username)) {
            //해당 메모 삭제
            postRepository.delete(post);
            return responseMethodDto.DeleteSuccessStatus();
        }else {
            throw new IllegalArgumentException("게시글을 작성한 사용자가 아닙니다.");
        }
    }
// 해당 메모가 DB에 존재하는지 확인
    private Post findPost(Long id){
      return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.")
        );
    }

    private Comment findComment(Long id){
        return commentRepository.findById(id).orElseThrow(() ->
                        new IllegalArgumentException("선택한 댓글은 존재하지 않습니다.")
                );
    }

    //댓글 작성
    public CommentResponseDto createComment(Long id,CommentRequestDto requestDto, String tokenValue) {
            //토큰 받아오기
        String token = jwtUtil.substringToken(tokenValue);
            //토큰 검증
        if(jwtUtil.validateToken(token)){
            //토큰에서 사용자 이름 가져오기
            Claims info = jwtUtil.getUserInfoFromToken(token);
            String username = info.getSubject();
            //토큰에서 가져온 사용자 이름을 그대로 댓글 사용자이름에 넣어주기
            requestDto.setUsername(username);
            //RequestDto -> Entity
            Comment comment = new Comment(requestDto);
            Post post = findPost(id);
            post.getCommentList().add(comment);
            //DB저장
            Comment saveComment = commentRepository.save(comment);
            Post saveComment2 = postRepository.save(post);
            //Entity -> ResponseDto
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            return commentResponseDto;
        }else{
            throw new IllegalArgumentException("토큰 에러 : 유효한 사용자가 아닙니다.");
        }
    }

    //댓글수정
    @Transactional
   public  CommentResponseDto updateComment(Long id, CommentRequestDto requestDto, String tokenValue){
       // 해당 댓글이 DB에 존재하는지 확인
       Comment comment = findComment(id);
       //쿠키에서 토큰 값을 가져와서 스트링 변수 token에 넣음
       String token = jwtUtil.substringToken(tokenValue);
       //토큰의 유효성 검증
       if(!jwtUtil.validateToken(token)){
           throw new IllegalArgumentException("Token Error");
       }
       //토큰 정보를 info에 담음
       Claims info = jwtUtil.getUserInfoFromToken(token);
       //토큰에서 사용자 권한 가져와서 어드민인지 체크하기
       String auth = (String)info.get(JwtUtil.AUTHORIZATION_KEY);
       //어드민이면 그냥 수정하고 리턴
       if(auth.equals("ADMIN")){
           comment.update(requestDto);
           CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
           return commentResponseDto;
       }
       //토큰에서 사용자 이름 가져오기
       String username = info.getSubject();
       //토큰의 사용자정보의 이름과 게시글의 사용자이름이 같은지 체크(본인이 쓴 글인지 확인)
       if(comment.getUsername().equals(username)) {
           //해당 메모 수정
           comment.update(requestDto);
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            return commentResponseDto;
       }else {
           throw new IllegalArgumentException("게시글을 작성한 사용자가 아닙니다.");
       }
   }

       //댓글삭제
      public ResponseEntity<ResponseMethodDto> deleteComment(Long id, String tokenValue){
          // 해당 댓글이 DB에 존재하는지 확인
          Comment comment = findComment(id);
          //쿠키에서 토큰 값을 가져와서 스트링 변수 token에 넣음
          String token = jwtUtil.substringToken(tokenValue);
          //토큰의 유효성 검증
          if(!jwtUtil.validateToken(token)){
              throw new IllegalArgumentException("Token Error");
          }
          Claims info = jwtUtil.getUserInfoFromToken(token);
          //토큰에서 사용자 권한 가져와서 어드민인지 체크하기
          String auth = (String)info.get(JwtUtil.AUTHORIZATION_KEY);
          //어드민이면 그냥 삭제하고 리턴
          if(auth.equals("ADMIN")){
              commentRepository.delete(comment);
              return responseMethodDto.DeleteSuccessStatus();
          }
          //토큰에서 사용자 이름 가져오기
          String username = info.getSubject();
          //토큰의 사용자정보의 이름과 게시글의 사용자이름이 같은지 체크(본인이 쓴 글인지 확인)
          if(comment.getUsername().equals(username)) {
              //해당 메모 삭제
              commentRepository.delete(comment);
              return responseMethodDto.DeleteSuccessStatus();
          }else {
              throw new IllegalArgumentException("댓글을 작성한 사용자가 아닙니다.");
          }
      }



}
