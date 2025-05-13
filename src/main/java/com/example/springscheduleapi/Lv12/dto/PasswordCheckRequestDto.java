package com.example.springscheduleapi.Lv12.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PasswordCheckRequestDto {
    @NotBlank
    private String password;
}
