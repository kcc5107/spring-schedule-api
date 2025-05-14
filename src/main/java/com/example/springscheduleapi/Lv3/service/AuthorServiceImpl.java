package com.example.springscheduleapi.Lv3.service;

import com.example.springscheduleapi.Lv3.dto.AuthorRequestDto;
import com.example.springscheduleapi.Lv3.dto.AuthorResponseDto;
import com.example.springscheduleapi.Lv3.entity.Author;
import com.example.springscheduleapi.Lv3.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorResponseDto createAuthor(AuthorRequestDto requestDto) {
        Author author = new Author(requestDto.getName(), requestDto.getEmail());
        return authorRepository.createAuthor(author);
    }
}
