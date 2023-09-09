package com.sparta.myblogbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class DeleteResponseDto {
    private int code;
    private HttpStatus httpStatus;
    private String message;
}
