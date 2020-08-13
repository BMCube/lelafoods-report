package edu.miu.lelafoods.batch.config;


import edu.miu.lelafoods.batch.model.Cart;
import edu.miu.lelafoods.batch.processor.CartItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<Cart> reader() {
        JdbcCursorItemReader<Cart> cursorItemReader = new JdbcCursorItemReader<>();
        cursorItemReader.setDataSource(dataSource);
        cursorItemReader.setSql("SELECT * FROM cart where status = 'DELIVERED';");
        cursorItemReader.setRowMapper(new CartRowMapper());
        return cursorItemReader;
    }

    @Bean
    public CartItemProcessor processor() {
        return new CartItemProcessor();
    }


    @Bean
    public FlatFileItemWriter<Cart> writer() {
        FlatFileItemWriter<Cart> writer = new FlatFileItemWriter<Cart>();
        writer.setResource(new ClassPathResource("order_report.csv"));

        DelimitedLineAggregator<Cart> lineAggregator = new DelimitedLineAggregator<Cart>();
        lineAggregator.setDelimiter(",");

        BeanWrapperFieldExtractor<Cart> fieldExtractor = new BeanWrapperFieldExtractor<Cart>();
        fieldExtractor.setNames(new String[]{"id","customerId", "restaurantId", "status"});
        lineAggregator.setFieldExtractor(fieldExtractor);
        writer.setLineAggregator(lineAggregator);
        return writer;
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1").<Cart, Cart>chunk(100)
                .reader(reader()).processor(processor()).writer(writer()).build();
    }

    @Bean
    public Job exportCartJob(){
        return jobBuilderFactory.get("exportCartJob")
                .incrementer(new RunIdIncrementer()).flow(step1()).end().build();

    }


}
