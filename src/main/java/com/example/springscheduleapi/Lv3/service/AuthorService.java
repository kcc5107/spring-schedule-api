package com.example.springscheduleapi.Lv3.service;

import com.example.springscheduleapi.Lv3.dto.AuthorRequestDto;
import com.example.springscheduleapi.Lv3.dto.AuthorResponseDto;

public interface AuthorService {
    AuthorResponseDto createAuthor(AuthorRequestDto requestDto);
}
