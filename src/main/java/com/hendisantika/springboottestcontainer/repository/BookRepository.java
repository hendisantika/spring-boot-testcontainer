package com.hendisantika.springboottestcontainer.repository;

import com.hendisantika.springboottestcontainer.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-testcontainer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/7/23
 * Time: 06:46
 * To change this template use File | Settings | File Templates.
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
