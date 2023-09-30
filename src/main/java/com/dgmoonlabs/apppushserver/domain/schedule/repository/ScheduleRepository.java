package com.dgmoonlabs.apppushserver.domain.schedule.repository;

import com.dgmoonlabs.apppushserver.domain.schedule.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    List<Schedule> findByDate(LocalDate date);
}
