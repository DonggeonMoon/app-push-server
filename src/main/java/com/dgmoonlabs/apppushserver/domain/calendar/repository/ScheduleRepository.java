package com.dgmoonlabs.apppushserver.domain.calendar.repository;

import com.dgmoonlabs.apppushserver.domain.calendar.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    List<Schedule> findByDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}
