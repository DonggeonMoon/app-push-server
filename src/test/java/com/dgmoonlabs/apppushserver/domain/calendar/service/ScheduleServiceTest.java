package com.dgmoonlabs.apppushserver.domain.calendar.service;

import com.dgmoonlabs.apppushserver.domain.calendar.model.Schedule;
import com.dgmoonlabs.apppushserver.domain.calendar.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class ScheduleServiceTest {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ScheduleRepository scheduleRepository;

    private static List<Schedule> schedules;

    @BeforeAll
    static void setUp() {
        schedules = List.of(
                Schedule.builder()
                        .id("a")
                        .dateTime(LocalDateTime.now())
                        .description("오늘 테스트")
                        .build(),
                Schedule.builder()
                        .id("b")
                        .dateTime(LocalDateTime.now().plusDays(1))
                        .description("내일 테스트")
                        .build()
        );
    }

    @Test
    @Order(2)
    public void getTodaySchedules() {
        scheduleService.getSchedulesOfToday()
                .forEach(schedule -> {
                    log.info(schedule.getId());
                    log.info(String.valueOf(schedule.getDateTime()));
                    log.info(schedule.getDescription());
                });
    }

    @Test
    @Order(1)
    void insertSchedules() {
        log.info(String.valueOf(new Date().toInstant().toEpochMilli()));
        log.info(String.valueOf(new Date().getTime()));
        List<Schedule> insertedSchedules = scheduleService.insertSchedules(schedules);

        log.info(insertedSchedules.toString());
        log.info(scheduleRepository.findById("a").get().toString());
        log.info(scheduleRepository.findById("b").get().toString());

        insertedSchedules.forEach(
                insertedSchedule -> {
                    assertThat(Optional.of(insertedSchedule)).isEqualTo(scheduleRepository.findById(insertedSchedule.getId()));
                }
        );
    }

    @Test
    @Order(3)
    void deleteSchedules() {
        scheduleService.deleteSchedules(schedules);
        schedules.forEach(
                schedule -> assertThat(scheduleRepository.findById(schedule.getId())).isEqualTo(Optional.empty())
        );
    }
}