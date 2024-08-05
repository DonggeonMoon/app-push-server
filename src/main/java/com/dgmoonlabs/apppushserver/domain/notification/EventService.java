package com.dgmoonlabs.apppushserver.domain.notification;

import com.dgmoonlabs.apppushserver.domain.event.dto.EventRequest;
import com.dgmoonlabs.apppushserver.domain.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    @Transactional
    public Long save(final EventRequest request) {
        return eventRepository.save(
                request.toEntity()
        ).getId();
    }
}
