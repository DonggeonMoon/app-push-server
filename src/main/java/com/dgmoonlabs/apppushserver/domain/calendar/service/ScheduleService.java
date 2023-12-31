package com.dgmoonlabs.apppushserver.domain.calendar.service;

import com.dgmoonlabs.apppushserver.domain.calendar.model.Schedule;
import com.dgmoonlabs.apppushserver.domain.calendar.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public List<Schedule> getSchedulesOfToday() {
        return scheduleRepository.findByDateTimeBetween(LocalDate.now().atStartOfDay(), LocalDate.now().atTime(23, 59, 59, 999999999));
    }

    public List<Schedule> insertSchedules(List<Schedule> schedules) {
        return scheduleRepository.insert(schedules);
    }

    public void deleteSchedules(List<Schedule> schedules) {
        scheduleRepository.deleteAll(schedules);
    }

    public void deleteAllSchedules() {
        scheduleRepository.deleteAll();
    }
}
