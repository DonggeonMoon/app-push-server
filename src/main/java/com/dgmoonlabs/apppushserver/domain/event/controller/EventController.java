package com.dgmoonlabs.apppushserver.domain.event.controller;

import com.dgmoonlabs.apppushserver.domain.event.dto.EventRequest;
import com.dgmoonlabs.apppushserver.domain.notification.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<String> createEvent(final @RequestBody EventRequest request) {
        return ResponseEntity.created(
                URI.create("/event/" + eventService.save(request))
        ).build();
    }
}
