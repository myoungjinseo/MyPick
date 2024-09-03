package com.develop.mypick.nutritionFact;

import com.develop.mypick.nutritionFact.dto.NutritionFactResponse;
import com.develop.mypick.nutritionFact.reader.NutritionFactReader;
import com.develop.mypick.nutritionFact.writer.NutritionFactJobWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class NutritionFactJobConfig {
    private final NutritionFactReader itemReader;
    private final NutritionFactJobWriter itemWriter;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job createNutritionFactJob(){
        return new JobBuilder("CreateNutritionFactStep",jobRepository)
                .start(createNutritionFactStep())
                .build();
    }

    @Bean
    @JobScope
    public Step createNutritionFactStep(){
        return new StepBuilder("CreateNutritionFactStep",jobRepository)
                .<List<NutritionFactResponse>,List<NutritionFactResponse>>chunk(10,transactionManager)
                .reader(itemReader)
                .writer(itemWriter)
                .allowStartIfComplete(true)
                .build();
    }


}
