package com.example.springscheduleapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ScheduleResponseDto {
    private Long id;
    private String toDo;
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
