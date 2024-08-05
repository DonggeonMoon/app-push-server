package com.dgmoonlabs.apppushserver.domain.event.dto;

import com.dgmoonlabs.apppushserver.domain.event.model.Event;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class EventRequest {
    private String name;
    private String description;
    private LocalDate date;
    private LocalTime time;

    public Event toEntity() {
        return Event.builder()
                .name(name)
                .description(description)
                .date(date)
                .time(time)
                .build();
    }
}
