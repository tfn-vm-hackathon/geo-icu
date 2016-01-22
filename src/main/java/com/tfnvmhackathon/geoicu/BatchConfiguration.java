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
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tfnvmhackathon.geoicu.process.Click;
import com.tfnvmhackathon.geoicu.process.ClickItemProcessor;

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
public class BatchConfiguration
{

	@Bean
	public ItemReader<Click> reader()
	{
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(com.cloudera.impala.jdbc41.Driver.class.getCanonicalName());
		dataSource.setUrl("jdbc:impala://10.1.10.212:21050/bi_test_1337");

		JdbcCursorItemReader<Click> reader = new JdbcCursorItemReader<Click>();
		reader.setDataSource(dataSource);
		reader.setRowMapper((rs, rowNum) -> new Click(rs.getString("domainCode"), rs.getLong("ip_address_decimal")));
		reader.setSql(this.getTrackingQuery());
		return reader;
	}

	private String getTrackingQuery()
	{
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT ip_address_decimal, domain_code ");
		sql.append(" FROM tracking ");
		sql.append(" WHERE psession_year = 2016 ");
		sql.append(" AND psession_month = 1 ");
		sql.append(" AND session_start_date = '2016-01-19';");
		return sql.toString();
	}

	@Bean
	public ItemProcessor<Click, Click> processor()
	{
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(com.mysql.jdbc.Driver.class.getCanonicalName());
		dataSource.setUrl("jdbc:mysql://10.1.10.252:4343/tfn_icu");
		dataSource.setUsername("root");
		dataSource.setPassword("");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		return new ClickItemProcessor(jdbcTemplate);
	}

	@Bean
	public ItemWriter<Click> writer(DataSource dataSource)
	{
		FlatFileItemWriter<Click> writer = new FlatFileItemWriter<Click>();
		writer.setShouldDeleteIfExists(true);
		writer.setResource(new FileSystemResource("result.csv"));
		writer.setLineAggregator(Click::toCsvString);
		return writer;
	}

	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Click> reader, ItemProcessor<Click, Click> processor, ItemWriter<Click> writer)
	{
		return stepBuilderFactory.get("step1").<Click, Click> chunk(100).reader(reader).processor(processor).writer(writer).build();
	}

	@Bean
	public Job importClickJob(JobBuilderFactory jobs, Step step1)
	{
		return jobs.get("importClickJob").incrementer(new RunIdIncrementer()).flow(step1).end().build();
	}

}
