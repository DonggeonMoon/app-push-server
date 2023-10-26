package com.dgmoonlabs.apppushserver.global.config.batch.job;

import com.dgmoonlabs.apppushserver.domain.calendar.facade.ScheduleFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
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
    private final ScheduleFacade scheduleFacade;

    @Bean
    public Job appPushJob() {
        return jobBuilderFactory.get("appPushJob")
                .start(appPushStep())
                .build();
    }

    @Bean
    public Step appPushStep() {
        return stepBuilderFactory.get("appPushStep")
                .tasklet((contribution, chunkContext) -> {
                    scheduleFacade.runTask();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
