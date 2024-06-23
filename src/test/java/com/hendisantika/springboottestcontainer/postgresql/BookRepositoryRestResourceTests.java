package com.hendisantika.springboottestcontainer.postgresql;

import com.hendisantika.springboottestcontainer.entity.Author;
import com.hendisantika.springboottestcontainer.entity.Book;
import com.hendisantika.springboottestcontainer.repository.BookRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.http.HttpStatus.OK;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-testcontainer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/7/23
 * Time: 06:45
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookRepositoryRestResourceTests {
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine3.17");
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @LocalServerPort
    private Integer port;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        bookRepository.deleteAll();
    }

    @Test
    @DisplayName("Entity will be created if datasource is available")
    void create() {
        var author = author();

        var book = book(author);

        ResponseEntity<Book> response = restTemplate.postForEntity("/books", book, Book.class);

        assertThat(response.getStatusCode()).isEqualTo(OK);
    }

    @Test
    void shouldGetAllBooks() {
        author();
        List<Book> books = List.of(
                book(author())
        );
        bookRepository.saveAll(books);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .body(".", hasSize(1));
    }

    private Author author() {
        var author = new Author();

        author.setName("Monkey D. Luffy");

        return author;
    }

    private Book book(final Author author) {
        var book = new Book();

        book.setId(1L);
        book.setAuthor(author);
        book.setTitle("The Jungle Book");

        return book;
    }
}
