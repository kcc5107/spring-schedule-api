package com.example.springscheduleapi.Lv3.repository;

import com.example.springscheduleapi.Lv3.dto.PasswordCheckRequestDto;
import com.example.springscheduleapi.Lv3.dto.ScheduleRequestDto;
import com.example.springscheduleapi.Lv3.dto.ScheduleResponseDto;
import com.example.springscheduleapi.Lv3.entity.Schedule;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto createSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule2").usingGeneratedKeyColumns("id");
        LocalDateTime now = LocalDateTime.now();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("toDo", schedule.getToDo());
        parameters.put("authorId", schedule.getAuthorId());
        parameters.put("password", schedule.getPassword());
        parameters.put("createdAt", now);
        parameters.put("updatedAt", now);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(), schedule.getToDo(), schedule.getAuthorId(), now, now);
    }

    @Override
    public List<ScheduleResponseDto> findSchedulesByFilter(Long authorId, LocalDateTime startDate, LocalDateTime endDate) {
        StringBuilder query = new StringBuilder();
        query.append("select * from schedule2 where 1=1");
        ArrayList<Object> params = new ArrayList<>();
        String sortDesc = " order by updatedAt desc";
//        log.info(authorId);
//        log.info(startDate);
//        log.info(endDate);

        if (authorId != null) {
            query.append(" and authorId = ?");
            params.add(authorId);
        }

        if (startDate != null) {
            query.append(" and updatedAt >= ? and updatedAt < ?");
            params.add(startDate);
            params.add(endDate);
        }

        query.append(sortDesc);
        return jdbcTemplate.query(query.toString(), scheduleRowMapper(), params.toArray());
    }

    @Override
    public Schedule findScheduleById(Long id) {
        List<Schedule> queryResult = jdbcTemplate.query("select * from schedule2 where id = ?", scheduleRowMapperV2(), id);
        return queryResult.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public int updateSchedule(Long id, ScheduleRequestDto requestDto) {
        return jdbcTemplate.update("update schedule2 set toDo = ?, updatedAt = ? where id = ? and password = ?",
                requestDto.getToDo(), LocalDateTime.now(), id, requestDto.getPassword());
    }

    @Override
    public int deleteSchedule(Long id, PasswordCheckRequestDto checkRequestDto) {
        return jdbcTemplate.update("delete from schedule2 where id = ? and password = ?", id, checkRequestDto.getPassword());
    }

    @Override
    public List<ScheduleResponseDto> findSchedulesByAuthorId(Long id) {
        return jdbcTemplate.query("select * from schedule2 where authorId = ? order by updatedAt desc", scheduleRowMapper(), id);
    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("toDo"),
                        rs.getLong("authorId"),
                        (LocalDateTime) rs.getObject("createdAt"),
                        (LocalDateTime) rs.getObject("updatedAt")
                );
            }
        };
    }

    private RowMapper<Schedule> scheduleRowMapperV2() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("toDo"),
                        rs.getLong("authorId"),
                        rs.getString("password"),
                        (LocalDateTime) rs.getObject("createdAt"),
                        (LocalDateTime) rs.getObject("updatedAt")
                );
            }
        };
    }

}