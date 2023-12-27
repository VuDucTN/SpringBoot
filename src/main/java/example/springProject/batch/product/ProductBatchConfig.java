package example.springProject.batch.product;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import Payload.ProductRequest;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class ProductBatchConfig {
	
	@Autowired
	private CustomProductItemWriter customProductItemWriter;
	
	public FlatFileItemReader<ProductRequest> reader(){
		FlatFileItemReader<ProductRequest> itemReader = new FlatFileItemReader<>();
		itemReader.setResource(new FileSystemResource("src/main/resources/products.csv"));
		itemReader.setName("csvReader");
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		return itemReader;
	}
	
	private LineMapper<ProductRequest> lineMapper(){
		DefaultLineMapper<ProductRequest> lineMapper = new DefaultLineMapper<ProductRequest>();
		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("name", "price","categoryId");
		
		BeanWrapperFieldSetMapper<ProductRequest> fileSetMapper = new BeanWrapperFieldSetMapper<ProductRequest>();
		fileSetMapper.setTargetType(ProductRequest.class);
		
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fileSetMapper);
		return lineMapper;
	}
	
	@Bean
	public ProductProcessor processor() {
		return new ProductProcessor();
	}
	
	
	
	@Bean
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("product-csv-step", jobRepository).<ProductRequest, ProductRequest>chunk(10, transactionManager)
				.reader(reader())
				.processor(processor())
				.writer(customProductItemWriter)
				.taskExecutor(taskExecutor())
				.build();
	}
	
	@Bean
	public Job importFileProduct(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new JobBuilder("importProduct", jobRepository)
				.flow(step1(jobRepository, transactionManager))
				.end()
				.build();
	}
	
	@Bean
	public TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
		asyncTaskExecutor.setConcurrencyLimit(10);
		return asyncTaskExecutor;
	}
}
