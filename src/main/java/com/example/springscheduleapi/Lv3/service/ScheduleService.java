package com.example.springscheduleapi.Lv3.service;

import com.example.springscheduleapi.Lv3.dto.PasswordCheckRequestDto;
import com.example.springscheduleapi.Lv3.dto.ScheduleRequestDto;
import com.example.springscheduleapi.Lv3.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(Long authorId, ScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findSchedulesByFilter(Long authorId, String updatedAt);

    ScheduleResponseDto findScheduleById(Long id);

    void updateSchedule(Long id, ScheduleRequestDto requestDto);

    void deleteSchedule(Long id, PasswordCheckRequestDto checkRequestDto);

    List<ScheduleResponseDto> findSchedulesByAuthorId(Long id);
}
