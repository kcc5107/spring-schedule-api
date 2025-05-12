package com.example.springscheduleapi.repository;

import com.example.springscheduleapi.dto.ScheduleResponseDto;
import com.example.springscheduleapi.entity.Schedule;

public interface ScheduleRepository {

    ScheduleResponseDto createSchedule(Schedule schedule);
}
