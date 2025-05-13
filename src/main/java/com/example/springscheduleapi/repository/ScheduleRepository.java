package com.example.springscheduleapi.repository;

import com.example.springscheduleapi.dto.ScheduleResponseDto;
import com.example.springscheduleapi.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository {

    ScheduleResponseDto createSchedule(Schedule schedule);

    List<ScheduleResponseDto> findSchedulesByFilter(String userName, LocalDateTime startDate, LocalDateTime endDate);
}
