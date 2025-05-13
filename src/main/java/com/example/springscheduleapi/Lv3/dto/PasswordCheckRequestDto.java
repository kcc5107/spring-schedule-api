package com.example.springscheduleapi.Lv3.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PasswordCheckRequestDto {
    @NotBlank
    private String password;
}
