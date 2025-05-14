package com.example.springscheduleapi.Lv3.repository;

import com.example.springscheduleapi.Lv3.dto.AuthorResponseDto;
import com.example.springscheduleapi.Lv3.entity.Author;

public interface AuthorRepository {
    AuthorResponseDto createAuthor(Author author);
}
