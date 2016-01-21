package com.tfnvmhackathon.geoicu;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tfnvmhackathon.geoicu.process.Click;
import com.tfnvmhackathon.geoicu.process.ClickItemProcessor;

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
public class BatchConfiguration
{

	@Bean
	public DataSource impalaDataSource()
	{
		// FIXME impala DatabaseType does not exist for Spring
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.cloudera.impala.jdbc41.Driver");
		dataSource.setUrl("jdbc:impala://10.1.10.212:21050/bi_test_1337");
		return dataSource;
	}

	@Bean
	public ItemReader<Click> reader(DataSource impalaDataSource)
	{
		JdbcCursorItemReader<Click> reader = new JdbcCursorItemReader<Click>();
		reader.setDataSource(impalaDataSource);
		reader.setRowMapper((rs, rowNum) -> new Click(rs.getLong("ip_address_decimal")));
		reader.setSql("SELECT * FROM tracking WHERE psession_year = 2016 AND psession_month = 1 AND session_start_date = '2016-01-19';");
		return reader;
	}

	@Bean
	public ItemProcessor<Click, Click> processor()
	{
		return new ClickItemProcessor();
	}

	@Bean
	public ItemWriter<Click> writer(DataSource dataSource)
	{
		FlatFileItemWriter<Click> writer = new FlatFileItemWriter<Click>();
		writer.setResource(new ClassPathResource("result.csv"));
		writer.setLineAggregator(item -> item.toCsvString());
		return writer;
	}

	// end::readerwriterprocessor[]

	// tag::jobstep[]
	@Bean
	public Job importClickJob(JobBuilderFactory jobs, Step s1)
	{
		return jobs.get("importClickJob").incrementer(new RunIdIncrementer()).flow(s1).end().build();
	}

	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Click> reader, ItemWriter<Click> writer, ItemProcessor<Click, Click> processor)
	{
		return stepBuilderFactory.get("step1").<Click, Click> chunk(10).reader(reader).processor(processor).writer(writer).build();
	}

	// end::jobstep[]

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource)
	{
		return new JdbcTemplate(dataSource);
	}

}
