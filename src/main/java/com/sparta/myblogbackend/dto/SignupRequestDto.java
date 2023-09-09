package com.sparta.myblogbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class SignupRequestDto {
    @NotBlank(message = "사용자 이름은 필수로 입력해야 합니다!")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{4,10}",message = "최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)로 구성되어야 합니다!")
    private String username;

    @NotBlank(message = "비밀번호는 필수로 입력해야 합니다!")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,15}",message = "최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자로 구성되어야 합니다!")
    private String password;

    private boolean admin = false;
    private String adminToken = "";
}