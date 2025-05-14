package com.example.springscheduleapi.Lv3.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class ScheduleRequestDto {
    @NotBlank
    @Length(max = 200)
    private String toDo;
    @NotBlank
    private String password;
}
