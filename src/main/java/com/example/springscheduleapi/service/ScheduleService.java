package com.example.springscheduleapi.service;

import com.example.springscheduleapi.dto.ScheduleRequestDto;
import com.example.springscheduleapi.dto.ScheduleResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findSchedulesByFilter(String userName, String updatedAt);

    ScheduleResponseDto findScheduleById(Long id);
}
