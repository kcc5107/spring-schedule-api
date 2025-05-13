package com.example.springscheduleapi.service;

import com.example.springscheduleapi.dto.ScheduleRequestDto;
import com.example.springscheduleapi.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findSchedulesByFilter(String userName, String updatedAt);
}
