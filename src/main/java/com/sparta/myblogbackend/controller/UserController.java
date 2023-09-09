package com.sparta.myblogbackend.controller;

import com.sparta.myblogbackend.dto.LoginRequestDto;
import com.sparta.myblogbackend.dto.ResponseMethodDto;
import com.sparta.myblogbackend.dto.SignupRequestDto;
import com.sparta.myblogbackend.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final ResponseMethodDto responseMethodDto;

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/user/signup")
    public ResponseEntity<ResponseMethodDto> signup(@RequestBody @Valid SignupRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return responseMethodDto.SignupFailStatus();
        }
        userService.signup(requestDto);
        return responseMethodDto.SignupSuccessStatus();
    }

    @PostMapping("/user/login")
    public ResponseEntity<ResponseMethodDto> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res){
        ResponseMethodDto response = new ResponseMethodDto();

        try {
            userService.login(requestDto, res);
        } catch (Exception e) {
            return responseMethodDto.LoginFailStatus();
        }
        return responseMethodDto.LoginSuccessStatus();
    }

}