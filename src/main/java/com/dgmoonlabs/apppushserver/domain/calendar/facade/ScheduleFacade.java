package com.dgmoonlabs.apppushserver.domain.calendar.facade;

import com.dgmoonlabs.apppushserver.domain.calendar.model.Schedule;
import com.dgmoonlabs.apppushserver.domain.calendar.service.FirebaseService;
import com.dgmoonlabs.apppushserver.domain.calendar.service.ScheduleService;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleFacade {
    private final FirebaseService firebaseService;
    private final ScheduleService scheduleService;

    public void runTask() {
        scheduleService.getSchedulesOfToday()
                .forEach(schedule -> {
                    firebaseService.sendMessage(
                            buildMessageFrom(schedule)
                    );
                });
    }

    private Message buildMessageFrom(Schedule schedule) {
        Notification notification = Notification.builder()
                .setTitle(schedule.getTitle())
                .setBody(schedule.getDescription())
                .build();

        return Message.builder()
                .setNotification(notification)
                .build();
    }
}
