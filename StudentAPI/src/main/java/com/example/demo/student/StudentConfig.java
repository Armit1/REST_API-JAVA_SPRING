package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student Guy = new Student("Guy", LocalDate.of(1943,Month.NOVEMBER,10), "guy@gmail.com");
            Student Guy2 = new Student( "Guy2", LocalDate.of(1996,Month.FEBRUARY,14), "guy2@gmail.com");

            repository.saveAll(List.of(Guy,Guy2));
        };
    }
}
