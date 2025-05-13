package com.example.springscheduleapi.repository;

import com.example.springscheduleapi.dto.ScheduleRequestDto;
import com.example.springscheduleapi.dto.ScheduleResponseDto;
import com.example.springscheduleapi.entity.Schedule;
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
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");
        LocalDateTime now = LocalDateTime.now();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("toDo", schedule.getToDo());
        parameters.put("userName", schedule.getUserName());
        parameters.put("password", schedule.getPassword());
        parameters.put("createdAt", now);
        parameters.put("updatedAt", now);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(), schedule.getToDo(), schedule.getUserName(), now, now);
    }

    @Override
    public List<ScheduleResponseDto> findSchedulesByFilter(String userName, LocalDateTime startDate, LocalDateTime endDate) {
        String query = "select * from schedule";
        String sortDesc = " order by updatedAt desc";
//        log.info(userName);
//        log.info(startDate);
//        log.info(endDate);

        if (userName != null && startDate != null) {
            return jdbcTemplate.query(query + " where userName = ? and updatedAt >= ? and updatedAt < ?" + sortDesc,
                    scheduleRowMapper(), userName, startDate, endDate);
        }

        if (userName != null) {
            return jdbcTemplate.query(query + " where userName = ?" + sortDesc, scheduleRowMapper(), userName);
        }

        if (startDate != null) {
            return jdbcTemplate.query(query + " where updatedAt >= ? and updatedAt < ?" + sortDesc, scheduleRowMapper(), startDate, endDate);
        }

        return jdbcTemplate.query(query + sortDesc, scheduleRowMapper());
    }

    @Override
    public Schedule findScheduleById(Long id) {
        List<Schedule> queryResult = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperV2(), id);
        return queryResult.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public int updateSchedule(Long id, ScheduleRequestDto requestDto) {
//        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return jdbcTemplate.update("update schedule set toDo = ?, userName = ?, updatedAt = ? where id = ? and password = ?",
                requestDto.getToDo(), requestDto.getUserName(), LocalDateTime.now(), id, requestDto.getPassword());
    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("toDo"),
                        rs.getString("userName"),
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
                        rs.getString("userName"),
                        rs.getString("password"),
                        (LocalDateTime) rs.getObject("createdAt"),
                        (LocalDateTime) rs.getObject("updatedAt")
                );
            }
        };
    }

}