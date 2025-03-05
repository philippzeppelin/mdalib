package com.philippzeppelin.mdalib.tests.integration;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Target(ElementType.TYPE)
//@Retention(RetentionPolicy.RUNTIME)
//@ActiveProfiles("Test")
//@SpringBootTest
//@Transactional
//@Sql({
//        "classpath:sql/data.sql"
//})
public abstract class IntegrationTestBase {

//    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:17.2");
//
//    @BeforeAll
//    public static void runContainer() {
//        container.start();
//    }
//
//    @DynamicPropertySource
//    static void setProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", container::getJdbcUrl);
//    }
}
