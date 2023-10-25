package com.dgmoonlabs.apppushserver.global.config.batch.job;

import com.dgmoonlabs.apppushserver.domain.calendar.model.Schedule;
import com.dgmoonlabs.apppushserver.domain.calendar.service.FirebaseService;
import com.dgmoonlabs.apppushserver.domain.calendar.service.ScheduleService;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ScheduleService scheduleService;
    private final FirebaseService firebaseService;

    @Bean
    public Job appPushJob() {
        JobParameters jobParameters = new JobParameters();

        return jobBuilderFactory.get("appPushJob")
                .start(appPushStep())
                .build();
    }

    @Bean
    public Step appPushStep() {
        return stepBuilderFactory.get("appPushStep")
                .tasklet((contribution, chunkContext) -> {
                    scheduleService.getSchedulesOfToday()
                            .forEach(schedule -> {
                                firebaseService.sendMessage(
                                        buildMessageFrom(schedule)
                                );
                            });
                    return RepeatStatus.FINISHED;
                })
                .build();
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
