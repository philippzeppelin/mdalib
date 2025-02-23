package com.philippzeppelin.mdalib;

import com.philippzeppelin.mdalib.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.DriverManager;


@SpringBootApplication
@ConfigurationPropertiesScan
@RequiredArgsConstructor
public class ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);
    }
}
