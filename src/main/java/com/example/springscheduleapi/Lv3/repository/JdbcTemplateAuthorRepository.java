package com.example.springscheduleapi.Lv3.repository;

import com.example.springscheduleapi.Lv3.dto.AuthorResponseDto;
import com.example.springscheduleapi.Lv3.entity.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcTemplateAuthorRepository implements AuthorRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateAuthorRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public AuthorResponseDto createAuthor(Author author) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("author").usingGeneratedKeyColumns("id");
        LocalDateTime now = LocalDateTime.now();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", author.getName());
        parameters.put("email", author.getEmail());
        parameters.put("createdAt", now);
        parameters.put("updatedAt", now);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new AuthorResponseDto(key.longValue(), author.getName(), author.getEmail(), now, now);
    }
}
