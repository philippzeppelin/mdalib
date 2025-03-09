package com.philippzeppelin.mdalib.tests.integration;


import com.philippzeppelin.mdalib.http.handler.exceptions.author.exception.AuthorBooksNotFoundException;
import com.philippzeppelin.mdalib.http.handler.exceptions.author.exception.AuthorNotFoundException;
import com.philippzeppelin.mdalib.service.AuthorService;
import com.philippzeppelin.mdalib.tests.integration.pojo.AuthorCreateRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorIntegrationTest {

    @MockitoSpyBean
    private AuthorService authorService;

    @LocalServerPort
    private int port;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/v1/authors";
    }

    //    http://localhost:8080/api/v1/authors
    @Test
    public void getAllAuthors_success() {
        RestAssured
                .given()
                .when()
                .get(getBaseUrl())
                .then()
                .body("[0].name", is("Александр Пушкин"))
                .body("[0].birthDate", is("1799-06-06"))
                .statusCode(200);
    }

    @Test
    public void getAllAuthors_ByName() {
        String searchWithAuthorName = "?name=Толстой";
        RestAssured
                .given()
                .when()
                .get(getBaseUrl() + searchWithAuthorName)
                .then()
                .body("size()", is(1))
                .body("[0].name", is("Лев Толстой"))
                .body("[0].birthDate", is("1828-09-09"))
                .statusCode(200);
    }

    @Test
    public void getAllAuthors_withPagination() {
        String searchWithPagination = "?page=0&size=2";
        RestAssured
                .given()
                .when()
                .get(getBaseUrl() + searchWithPagination)
                .then()
                .body("size()", is(2))
                .body("[0].name", is("Александр Пушкин"))
                .body("[0].birthDate", is("1799-06-06"))
                .statusCode(200);
    }

    @Test
    public void getAllAuthors_withNegativePagination() {
        String searchWithNegativePagination = "?page=-1&size=-2";
        RestAssured
                .given()
                .when()
                .get(getBaseUrl() + searchWithNegativePagination)
                .then()
                .statusCode(400);
    }

    @Test
    public void getAllAuthors_emptyResult() {
        when(authorService.getAuthors(null, 0, 10))
                .thenThrow(new AuthorNotFoundException("Authors not found"));
        RestAssured
                .given()
                .when()
                .get(getBaseUrl())
                .then()
                .statusCode(404);
    }

    @Test
    public void getAllAuthors_withZeroSize() {
        String searchWithNegativePagination = "?page=0&size=0";
        RestAssured
                .given()
                .when()
                .get(getBaseUrl() + searchWithNegativePagination)
                .then()
                .statusCode(400);
    }

    // POST /api/v1/authors
    @Test
    public void addAuthor_success() {
        AuthorCreateRequest request = new AuthorCreateRequest();
        request.setName("Муса Джалиль");
        request.setBirthDate(LocalDate.of(1906, 2, 15));

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(getBaseUrl())
                .then()
                .statusCode(201)
                .body("name", is("Муса Джалиль"))
                .body("birthDate", is("1906-02-15"));
    }

    @Test
    public void addAuthor_incorrectJson() {
        String incorrectJson = "{ \"name\": \"Test\", \"birthDate\": \"invalid-date\" }";
        RestAssured
                .given()
                .contentType("application/json")
                .body(incorrectJson)
                .when()
                .post(getBaseUrl())
                .then()
                .statusCode(400);
    }

    //    GET /api/v1/authors/{id}/books
    @Test
    public void getBooksByAuthorId_success() {
        long authorId = 1L;
        RestAssured
                .given()
                .when()
                .get(getBaseUrl() + "/" + authorId + "/books")
                .then()
                .statusCode(200)
                .body("size()", is(6))
                .body("[0].title", is("Евгений Онегин"))
                .body("[0].publicationYear", is(1833))
                .body("[0].authorId", is(1))
                .body("[0].availabilityIds", contains(1, 2, 4));
    }

    @Test
    public void getBooksByAuthorId_emptyResult() {
        long authorId = 1L;
        when(authorService.findBooksByAuthorId(1L))
                .thenThrow(new AuthorBooksNotFoundException("Books not found"));
        RestAssured
                .given()
                .when()
                .get(getBaseUrl() + "/" + authorId + "/books")
                .then()
                .statusCode(404);
    }

    @Test
    public void getBooksByAuthorId_withNotExistingAuthor() {
        long authorId = 999L;
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(getBaseUrl() + "/" + authorId + "/books")
                .then()
                .statusCode(404);
    }
}
