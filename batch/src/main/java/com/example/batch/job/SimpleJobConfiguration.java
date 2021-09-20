package com.example.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SimpleJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    //Job은 하나의 배치 작업 단위
    //Job 안에는 여러 Step이 존재하고, Step 안에 Tasklet 혹은 Reader & Processor & Writer 묶음이 존재
    @Bean
    public Job simpleJob(){
        return jobBuilderFactory.get("simpleJob") //simpleJob이란 임의의 이름의 Batch job 생성
                .start(simpleStep1(null)) //simpleStep1 이란 이름의 Batch Step을 생성
                .build();
    }

    @Bean
    @JobScope
    public Step simpleStep1(@Value("#{jobParameters[requestDate]}") String requestDate){
        return stepBuilderFactory.get("simpleStep1")
                //Tasklet은 어찌보면 Spring MVC의 @Component, @Bean과 비슷한 역할이라고 보셔도 될 것 같습니다.
                //명확한 역할은 없지만, 개발자가 지정한 커스텀한 기능을 위한 단위
                .tasklet(((contribution, chunkContext) -> { //Tasklet은 Step안에서 단일로 수행될 커스텀한 기능들을 선언할때 사용
                    log.info(">>> This is Step1");
                    log.info(">>> requestDate = {}",requestDate);
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }
}
