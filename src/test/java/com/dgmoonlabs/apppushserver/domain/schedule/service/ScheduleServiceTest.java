package com.dgmoonlabs.apppushserver.domain.schedule.service;

import com.dgmoonlabs.apppushserver.domain.schedule.model.Schedule;
import com.dgmoonlabs.apppushserver.domain.schedule.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
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
                        .date(LocalDate.now())
                        .description("오늘 테스트")
                        .build(),
                Schedule.builder()
                        .id("b")
                        .date(LocalDate.now().plusDays(1))
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
                    log.info(String.valueOf(schedule.getDate()));
                    log.info(schedule.getDescription());
                });
    }

    @Test
    @Order(1)
    void insertSchedules() {
        List<Schedule> insertedSchedules = scheduleService.insertSchedules(schedules);

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

    @Test
    @Order(4)
    @Disabled(value = "not for production")
    void deleteAllSchedules() {
        scheduleService.deleteAllSchedules();
        assertThat(scheduleRepository.findAll().size()).isEqualTo(0);
    }
}