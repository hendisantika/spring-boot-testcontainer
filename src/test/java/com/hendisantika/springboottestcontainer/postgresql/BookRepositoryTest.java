package com.hendisantika.springboottestcontainer.postgresql;

import com.hendisantika.springboottestcontainer.entity.Author;
import com.hendisantika.springboottestcontainer.entity.Book;
import com.hendisantika.springboottestcontainer.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-testcontainer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/7/23
 * Time: 18:53
 * To change this template use File | Settings | File Templates.
 */
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // deactivate the default behaviour
@DataJpaTest
class BookRepositoryTest {
    @Container
    static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres:17beta1-alpine3.20")
            .withDatabaseName("test")
            .withUsername("yuji")
            .withPassword("S3cret");
    @Autowired
    private BookRepository bookRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlContainer::getUsername);
        registry.add("spring.datasource.password", postgresqlContainer::getPassword);
    }

    @Test
    public void should_be_able_to_get_all_books() {
        var author = new Author();

        author.setName("Monkey D. Luffy");
        //arrange
        Book book1 = new Book(1L, author, "Java");
        Book book2 = new Book(2L, author, "Kotlin");
        bookRepository.saveAll(List.of(book1, book2));
        //act
        List<Book> books = new ArrayList<>();
        books = bookRepository.findAll();
        //assert
        Assertions.assertThat(books).hasSize(2);
        Assertions.assertThat(books.get(0).getTitle()).isEqualTo("Java");
    }
}
