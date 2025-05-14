package com.example.springscheduleapi.Lv3.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class AuthorRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
}
