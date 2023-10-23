package com.dgmoonlabs.apppushserver.domain.schedule.service;

import com.dgmoonlabs.apppushserver.domain.schedule.model.Schedule;
import com.dgmoonlabs.apppushserver.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public List<Schedule> getSchedulesOfToday() {
        return scheduleRepository.findByDate(LocalDate.now());
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
