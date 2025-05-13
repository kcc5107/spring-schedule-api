package com.example.springscheduleapi.Lv12.service;

import com.example.springscheduleapi.Lv12.dto.PasswordCheckRequestDto;
import com.example.springscheduleapi.Lv12.dto.ScheduleRequestDto;
import com.example.springscheduleapi.Lv12.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findSchedulesByFilter(String userName, String updatedAt);

    ScheduleResponseDto findScheduleById(Long id);

    void updateSchedule(Long id, ScheduleRequestDto requestDto);

    void deleteSchedule(Long id, PasswordCheckRequestDto checkRequestDto);
}
