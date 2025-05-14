package com.example.springscheduleapi.Lv3.controller;

import com.example.springscheduleapi.Lv3.dto.AuthorRequestDto;
import com.example.springscheduleapi.Lv3.dto.AuthorResponseDto;
import com.example.springscheduleapi.Lv3.dto.ScheduleRequestDto;
import com.example.springscheduleapi.Lv3.dto.ScheduleResponseDto;
import com.example.springscheduleapi.Lv3.service.AuthorService;
import com.example.springscheduleapi.Lv3.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorScheduleController {
    private final ScheduleService scheduleService;
    private final AuthorService authorService;

    public AuthorScheduleController(ScheduleService scheduleService, AuthorService authorService) {
        this.scheduleService = scheduleService;
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(@Valid @RequestBody AuthorRequestDto requestDto) {
        return new ResponseEntity<>(authorService.createAuthor(requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> findScheduleByAuthorId(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findSchedulesByAuthorId(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/schedules")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleRequestDto requestDto) {
        return new ResponseEntity<>(scheduleService.createSchedule(id, requestDto), HttpStatus.CREATED);
    }
}
