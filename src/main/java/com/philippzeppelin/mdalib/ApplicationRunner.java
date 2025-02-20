package com.philippzeppelin.mdalib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationRunner.class, args);
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

}
