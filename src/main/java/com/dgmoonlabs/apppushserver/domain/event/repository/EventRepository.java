package com.dgmoonlabs.apppushserver.domain.event.repository;

import com.dgmoonlabs.apppushserver.domain.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByDateAndTimeBetween(LocalDate date, LocalTime start, LocalTime end);
}
