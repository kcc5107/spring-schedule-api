package com.example.springscheduleapi.Lv3.controller;

import com.example.springscheduleapi.Lv3.dto.PasswordCheckRequestDto;
import com.example.springscheduleapi.Lv3.dto.ScheduleRequestDto;
import com.example.springscheduleapi.Lv3.dto.ScheduleResponseDto;
import com.example.springscheduleapi.Lv3.service.ScheduleService;
import jakarta.validation.Valid;
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

//    @PostMapping
//    public ResponseEntity<ScheduleResponseDto> createSchedule(@Valid @RequestBody ScheduleRequestDto requestDto) {
//        return new ResponseEntity<>(scheduleService.createSchedule(requestDto), HttpStatus.CREATED);
//    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findSchedulesByFilter(@RequestParam(required = false) Long authorId,
                                                                           @RequestParam(required = false) String updatedAt) {
        return new ResponseEntity<>(scheduleService.findSchedulesByFilter(authorId, updatedAt), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleRequestDto requestDto) {
        scheduleService.updateSchedule(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @Valid @RequestBody PasswordCheckRequestDto checkRequestDto) {
        scheduleService.deleteSchedule(id, checkRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
