package com.dgmoonlabs.apppushserver.domain.notification.service;

import com.dgmoonlabs.apppushserver.domain.event.repository.EventRepository;
import com.dgmoonlabs.apppushserver.domain.notification.model.NotificationRequest;
import com.dgmoonlabs.apppushserver.global.util.FirebaseUtils;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final EventRepository eventRepository;

    public void send(final NotificationRequest request) {
        FirebaseUtils.sendMessage(
                Message.builder()
                        .setTopic(request.getTopicName())
                        .setNotification(
                                Notification.builder()
                                        .setTitle(request.getTitle())
                                        .setBody(request.getBody())
                                        .setImage(request.getImageUrl())
                                        .build()
                        ).build()
        );
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void sendEventNotification() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDate currentDate = currentDateTime.toLocalDate();
        LocalTime startTime = currentDateTime.toLocalTime().truncatedTo(ChronoUnit.MINUTES);
        LocalTime endTime = startTime.plusMinutes(1);
        eventRepository.findByDateAndTimeBetween(
                currentDate,
                startTime,
                endTime
        ).ifPresent(event -> FirebaseUtils.sendMessage(
                Message.builder()
                        .setTopic("DAILY_STOCK_SCHEDULE")
                        .setNotification(
                                Notification.builder()
                                        .setTitle(event.getName())
                                        .setBody(event.getDescription())
                                        .build()
                        ).build()
        ));
    }
}
