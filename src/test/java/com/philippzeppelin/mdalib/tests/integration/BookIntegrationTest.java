package com.philippzeppelin.mdalib.tests.integration;

import com.philippzeppelin.mdalib.tests.integration.pojo.BookCreateRequest;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookIntegrationTest {

    @LocalServerPort
    private int port;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/v1/books";
    }

    //    http://localhost:8080/api/v1/books
    @Test
    public void addBook_success() {
        BookCreateRequest request = new BookCreateRequest();
        request.setTitle("Руслан и Людмила");
        request.setPublicationYear(1820);
        request.setAuthorId(1L);
        request.setAvailabilityIds(List.of(1L, 2L));

        RestAssured
                .given()
                .contentType("application/json")
                .body(request)
                .when()
                .post(getBaseUrl())
                .then()
                .statusCode(201)
                .body("title", is("Руслан и Людмила"))
                .body("publicationYear", is(1820))
                .body("authorId", is(1))
                .body("availabilityIds", is(Matchers.contains(1, 2)));
    }

    @Test
    public void addBook_incorrectJson() {
        String incorrectJson = "{ \"title\": \"Руслан и Людмила\", \"setPublicationYear\": \"1820\", " +
                               "\"authorId\": 1, \"availabilityIds\": invalid-availabilityIds }";

        RestAssured
                .given()
                .contentType("application/json")
                .body(incorrectJson)
                .when()
                .post(getBaseUrl())
                .then()
                .statusCode(400);
    }

    @Test
    public void addBook_nullParam() {
        BookCreateRequest request = new BookCreateRequest();
        request.setTitle(null);
        request.setPublicationYear(1820);
        request.setAuthorId(1L);
        request.setAvailabilityIds(List.of(1L, 2L));

        RestAssured
                .given()
                .contentType("application/json")
                .body(request)
                .when()
                .post(getBaseUrl())
                .then()
                .statusCode(500);
    }

    @Test
    public void addBook_authorNotExists() {
        BookCreateRequest request = new BookCreateRequest();
        request.setTitle("Руслан и Людмила");
        request.setPublicationYear(1820);
        request.setAuthorId(999L);
        request.setAvailabilityIds(List.of(1L, 2L));

        RestAssured
                .given()
                .contentType("application/json")
                .body(request)
                .when()
                .post(getBaseUrl())
                .then()
                .statusCode(404);
    }

    //    http://localhost:8080/api/v1/books/27
    @Test
    public void deleteBook_success() {
        long bookId = 1L;
        RestAssured
                .given()
                .when()
                .delete(getBaseUrl() + "/" + bookId)
                .then()
                .statusCode(204);
    }

    @Test
    public void deleteBook_notFoundId() {
        long bookId = 999L;
        RestAssured
                .given()
                .when()
                .delete(getBaseUrl() + "/" + bookId)
                .then()
                .statusCode(404);
    }
}
