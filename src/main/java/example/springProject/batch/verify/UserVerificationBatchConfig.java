package example.springProject.batch.verify;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


import example.springProject.models.User;
import example.springProject.repository.UserRepository;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class UserVerificationBatchConfig {
	private UserRepository userRepository;

    @Autowired
    private UserVerificationReader userVerificationReader;

    @Autowired
    private UserVerificationProcessor userVerificationProcessor;

    @Bean
    public Step userVerificationStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("userVerificationStep",jobRepository).<User, User>chunk(10,transactionManager)
                .reader(userVerificationReader)
                .processor(userVerificationProcessor)
                .writer(writer())
                .build();
    }

    @Bean
    public Job userVerificationJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("userVerificationJob",jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(userVerificationStep(jobRepository,transactionManager))
                .end()
                .build();
    }
    
    @Bean(name = "userVerificationWriter")
	public RepositoryItemWriter<User> writer(){
		RepositoryItemWriter<User> writer = new RepositoryItemWriter<User>();
		writer.setRepository(userRepository);
		writer.setMethodName("save");
		return writer;
	}
}
