package com.dgmoonlabs.apppushserver.domain.calendar.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootTest(classes = BatchTest.TestConfig.class)
public class BatchTest {
    @Autowired
    JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    void Test() throws Exception {
        jobLauncherTestUtils.launchJob(
                new JobParametersBuilder()
                        .addParameter("job1", new JobParameter("job1"))
                        .toJobParameters()
        );
    }

    @Configuration
    @EnableBatchProcessing
    @RequiredArgsConstructor
    static class TestConfig {
        private final JobBuilderFactory jobBuilderFactory;
        private final StepBuilderFactory stepBuilderFactory;

        @Bean
        public Job newJob() {
            Step step = stepBuilderFactory.get("newStep")
                    .tasklet((contribution, chunkContext) -> {
                        System.out.println("chunkContext.getStepContext().getJobParameters().get(\"newJob\") = " + chunkContext.getStepContext().getJobParameters().get("newJob"));
                        return RepeatStatus.FINISHED;
                    })
                    .build();
            return jobBuilderFactory.get("newJob")
                    .start(step)
                    .build();
        }

        @Bean
        public JobLauncherTestUtils jobLauncherTestUtils() {
            return new JobLauncherTestUtils();
        }
    }

}
