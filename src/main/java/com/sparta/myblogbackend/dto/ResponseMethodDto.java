package com.sparta.myblogbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
@Setter
@Component
public class ResponseMethodDto {
    private int code;
    private HttpStatus httpStatus;
    private String message;

    public ResponseMethodDto() {}
    public ResponseEntity<ResponseMethodDto> SignupSuccessStatus(){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = "회원가입 성공!";
        return new ResponseEntity<>(this, this.getHttpStatus());
    }

    public ResponseEntity<ResponseMethodDto> LoginSuccessStatus(){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = "로그인 성공!";
        return new ResponseEntity<>(this, this.getHttpStatus());
    }

    public ResponseEntity<ResponseMethodDto> SignupFailStatus(){
        this.code = 400;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.message = "회원가입 실패!";
        return new ResponseEntity<>(this, this.getHttpStatus());
    }

    public ResponseEntity<ResponseMethodDto> LoginFailStatus(){
        this.code = 400;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.message = "로그인 실패!";
        return new ResponseEntity<>(this, this.getHttpStatus());
    }

    public ResponseEntity<ResponseMethodDto> DeleteSuccessStatus(){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = "게시글or댓글 삭제 성공!";
        return new ResponseEntity<>(this, this.getHttpStatus());
    }

    public ResponseEntity<ResponseMethodDto> DeleteFailStatus(){
        this.code = 400;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.message = "게시글or댓글 삭제 실패!";
        return new ResponseEntity<>(this, this.getHttpStatus());
    }
}
