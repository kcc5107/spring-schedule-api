package com.example.springscheduleapi.controller;

import com.example.springscheduleapi.dto.ScheduleRequestDto;
import com.example.springscheduleapi.dto.ScheduleResponseDto;
import com.example.springscheduleapi.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return new ResponseEntity<>(scheduleService.createSchedule(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ScheduleResponseDto> findSchedulesByFilter(@RequestParam(required = false) String userName, @RequestParam(required = false) String updatedAt) {
        return scheduleService.findSchedulesByFilter(userName, updatedAt);
    }
}
