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

    @Bean
    public CommandLineRunner commandLineRunner(AuthorRepository authorRepository) {
        return args -> {
            testConnection();
//            test(authorRepository);
        };
    }

    private static void testConnection() {
        String url = "jdbc:postgresql://localhost:5433/postgres";
        String user = "postgres";
        String password = "pass";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection successful!");
        } catch (Exception e) {
            System.out.println("Connection is not successful!");
            e.printStackTrace();
        }
    }

    private static void test(AuthorRepository authorRepository) {
        authorRepository.findAll().forEach(System.out::println);
    }
}
