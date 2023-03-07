package com.hendisantika.springboottestcontainer.controller;

import com.hendisantika.springboottestcontainer.entity.Book;
import com.hendisantika.springboottestcontainer.repository.BookRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-testcontainer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/7/23
 * Time: 06:47
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;

    @GetMapping
    List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @PostMapping
    Book addNewBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }
}
