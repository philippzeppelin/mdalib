package com.philippzeppelin.mdalib.tests.integration;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AvailabilityIntegrationTest {

    @LocalServerPort
    private int port;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/v1/availabilities";
    }

    @Test
    public void getAllAvailabilities() {
        RestAssured
                .given()
                .when()
                .get(getBaseUrl())
                .then()
                .body("[0].location", is("Главный склад"))
                .body("[0].quantity", is(100))
                .statusCode(200);
    }
}
