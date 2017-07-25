
package com.lmsbatch;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Date;
import java.util.stream.Stream;

@Configuration
@EnableBatchProcessing
@Import({BatchScheduler.class})
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @Autowired
    private SimpleJobLauncher jobLauncher;


    @Autowired
    JobCompletionNotificationListener listener;

    @Autowired
    public DataSource dataSource;

    // tag::readerwriterprocessor[]
    @Bean
    public FlatFileItemReader<Person> reader() throws IOException {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();

        String baseUrl = "https://raw.githubusercontent.com/chakra/lmsbatchjob/master/src/main/resources/50empdata.csv";

        URL url = new URL(baseUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (connection.getResponseCode() == 200) {
            FileUtils.copyURLToFile(url, new File("src/main/resources/emp.csv"));
        }

        reader.setResource(new ClassPathResource("emp.csv"));
        reader.setLineMapper(new DefaultLineMapper<Person>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "shrStaffID", "preferredLastName","preferredFirstName", "conflictGroup", "employeeCode", "businessUnit", "state", "team", "managerReference", "secondLevelManager", "roleLocation", "emailAddressWork","active"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }});
        }});
        return reader;
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Person> writer() {
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
        writer.setSql("INSERT INTO people (SHRStaffID,PreferredLastName,PreferredFirstName,ConflictGroup,EmployeeCode,BusinessUnit,State,Team,ManagerReference,SecondLevelManager,RoleLocation,EmailAddressWork,Active) " +
                "VALUES (:shrStaffID, :preferredLastName, :preferredFirstName, :conflictGroup, :employeeCode, :businessUnit, :state, :team, :managerReference, :secondLevelManager, :roleLocation, :emailAddressWork, :active)");
        writer.setDataSource(dataSource);
        return writer;
    }
    // end::readerwriterprocessor[]
//    "0 0 * * * *" = the top of every hour of every day.
//     "*/10 * * * * *" = every ten seconds.
//     "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
//     "0 0 6,19 * * *" = 6:00 AM and 7:00 PM every day.
//     "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30, 10:00 and 10:30 every day.
//     "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
//     "0 0 0 25 12 ?" = every Christmas Day at midnight

    @Scheduled(cron = "0 0 * * * *")
    public void perform() throws Exception {

        System.out.println("Job Started at :" + new Date());

        JobParameters param = new JobParametersBuilder().addString("JobID",
                String.valueOf(System.currentTimeMillis())).toJobParameters();

        JobExecution execution = jobLauncher.run(importUserJob(), param);

        System.out.println("Job finished with status :" + execution.getStatus());
    }

    // tag::jobstep[]
    @Bean
    public Job importUserJob() throws IOException {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() throws IOException {
        return stepBuilderFactory.get("step1")
                .<Person, Person> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

       // end::jobstep[]
}


