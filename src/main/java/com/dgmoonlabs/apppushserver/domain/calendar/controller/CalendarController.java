package com.dgmoonlabs.apppushserver.domain.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class CalendarController {
    private final Job job;
    private final JobLauncher jobLauncher;

    @Scheduled(cron = "0 0 * * * *")
    public void executeSchedule() {
        JobParameters jobParameter = new JobParametersBuilder()
                .addParameter("uuid", new JobParameter(UUID.randomUUID().toString()))
                .toJobParameters();
        runJob(job, jobParameter);
    }

    private void runJob(Job job, JobParameters jobParameter) {
        try {
            jobLauncher.run(job, jobParameter);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }
}
