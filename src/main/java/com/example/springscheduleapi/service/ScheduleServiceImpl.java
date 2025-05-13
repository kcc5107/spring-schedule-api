package com.example.springscheduleapi.service;

import com.example.springscheduleapi.dto.ScheduleRequestDto;
import com.example.springscheduleapi.dto.ScheduleResponseDto;
import com.example.springscheduleapi.entity.Schedule;
import com.example.springscheduleapi.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto.getToDo(), requestDto.getUserName(), requestDto.getPassword());
        return scheduleRepository.createSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findSchedulesByFilter(String userName, String updatedAt) {
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;

        if (updatedAt != null) {
            LocalDate localDate = LocalDate.parse(updatedAt, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            startDate = localDate.atStartOfDay();
            endDate = localDate.plusDays(1).atStartOfDay();
        }

        return scheduleRepository.findSchedulesByFilter(userName, startDate, endDate);
    }
}
