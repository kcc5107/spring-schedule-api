package com.example.springscheduleapi.Lv3.repository;

import com.example.springscheduleapi.Lv3.dto.PasswordCheckRequestDto;
import com.example.springscheduleapi.Lv3.dto.ScheduleRequestDto;
import com.example.springscheduleapi.Lv3.dto.ScheduleResponseDto;
import com.example.springscheduleapi.Lv3.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository {

    ScheduleResponseDto createSchedule(Schedule schedule);

    List<ScheduleResponseDto> findSchedulesByFilter(Long authorId, LocalDateTime startDate, LocalDateTime endDate);

    Schedule findScheduleById(Long id);

    int updateSchedule(Long id, ScheduleRequestDto requestDto);

    int deleteSchedule(Long id, PasswordCheckRequestDto checkRequestDto);

    List<ScheduleResponseDto> findSchedulesByAuthorId(Long id);
}
