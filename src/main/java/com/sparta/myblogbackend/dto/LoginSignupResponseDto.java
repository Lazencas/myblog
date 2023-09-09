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
public class LoginSignupResponseDto {
    private int code;
    private HttpStatus httpStatus;
    private String message;

    public LoginSignupResponseDto() {

    }
    public ResponseEntity<LoginSignupResponseDto> SignupSuccessStatus(){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = "회원가입 성공!";
        return new ResponseEntity<>(this, this.getHttpStatus());
    }

    public ResponseEntity<LoginSignupResponseDto> LoginSuccessStatus(){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = "로그인 성공!";
        return new ResponseEntity<>(this, this.getHttpStatus());
    }

    public ResponseEntity<LoginSignupResponseDto> SignupFailStatus(){
        this.code = 400;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.message = "회원가입 실패!";
        return new ResponseEntity<>(this, this.getHttpStatus());
    }

    public ResponseEntity<LoginSignupResponseDto> LoginFailStatus(){
        this.code = 400;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.message = "로그인 실패!";
        return new ResponseEntity<>(this, this.getHttpStatus());
    }
}
