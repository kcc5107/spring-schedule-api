package com.example.springscheduleapi.Lv12.service;

import com.example.springscheduleapi.Lv12.dto.PasswordCheckRequestDto;
import com.example.springscheduleapi.Lv12.dto.ScheduleRequestDto;
import com.example.springscheduleapi.Lv12.dto.ScheduleResponseDto;
import com.example.springscheduleapi.Lv12.entity.Schedule;
import com.example.springscheduleapi.Lv12.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findScheduleById(id);
        return new ScheduleResponseDto(schedule.getId(), schedule.getToDo(), schedule.getUserName(), schedule.getCreatedAt(), schedule.getUpdatedAt());
    }

    @Override
    public void updateSchedule(Long id, ScheduleRequestDto requestDto) {
        int updatedRow = scheduleRepository.updateSchedule(id, requestDto);
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
        }
    }

    @Override
    public void deleteSchedule(Long id, PasswordCheckRequestDto checkRequestDto) {
        int deletedRow = scheduleRepository.deleteSchedule(id, checkRequestDto);
        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been deleted.");
        }
    }
}
