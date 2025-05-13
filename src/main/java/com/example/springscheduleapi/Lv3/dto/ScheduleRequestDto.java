package com.example.springscheduleapi.Lv3.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    @NotBlank
    private String toDo;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
