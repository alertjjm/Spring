package com.example.springbatchexample.jobs;

import com.example.springbatchexample.tasklets.TutorialTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TutorialConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job tutorialJob(){
        return jobBuilderFactory.get("tutorialJob")
                .start(tutorialStep())
                .build();
    }

    @Bean
    public Step tutorialStep(){
        return stepBuilderFactory.get("tutorialStep")
                .tasklet(new TutorialTasklet())
                .build();
    }
}
