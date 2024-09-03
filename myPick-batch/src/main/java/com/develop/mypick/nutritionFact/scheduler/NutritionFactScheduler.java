package com.develop.mypick.nutritionFact.scheduler;

import com.develop.mypick.nutritionFact.NutritionFactJobConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class NutritionFactScheduler {
    private final NutritionFactJobConfig nutritionFactJobConfig;
    private final JobLauncher jobLauncher;


    @Scheduled(cron = "0 15 1 L * ?")
    public void run() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        jobLauncher.run(
                nutritionFactJobConfig.createNutritionFactJob(),
                new JobParametersBuilder()
                        .addString("time", LocalDateTime.now().toString())
                        .toJobParameters()
        );
    }

}

