package com.dgmoonlabs.apppushserver.domain.calendar.facade;

import com.dgmoonlabs.apppushserver.domain.calendar.service.FirebaseService;
import com.dgmoonlabs.apppushserver.domain.calendar.service.ScheduleService;
import com.dgmoonlabs.apppushserver.global.enums.TopicName;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleFacade {
    private final FirebaseService firebaseService;
    private final ScheduleService scheduleService;

    public void send() {
        scheduleService.getSchedulesOfToday()
                .forEach(i -> {
                    firebaseService.sendMessage(
                            Message.builder()
                                    .setNotification(
                                            Notification.builder()
                                                    .setTitle(i.getTitle())
                                                    .setBody(i.getDescription())
                                                    .build()
                                    ).setTopic(TopicName.ALL_USERS.getValue())
                                    .build()
                    );
                });
    }
}
