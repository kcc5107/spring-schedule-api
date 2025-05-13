package com.example.springscheduleapi.Lv12.repository;

import com.example.springscheduleapi.Lv12.dto.PasswordCheckRequestDto;
import com.example.springscheduleapi.Lv12.dto.ScheduleRequestDto;
import com.example.springscheduleapi.Lv12.dto.ScheduleResponseDto;
import com.example.springscheduleapi.Lv12.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository {

    ScheduleResponseDto createSchedule(Schedule schedule);

    List<ScheduleResponseDto> findSchedulesByFilter(String userName, LocalDateTime startDate, LocalDateTime endDate);

    Schedule findScheduleById(Long id);

    int updateSchedule(Long id, ScheduleRequestDto requestDto);

    int deleteSchedule(Long id, PasswordCheckRequestDto checkRequestDto);
}
