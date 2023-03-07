package com.hendisantika.springboottestcontainer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

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
@Data
@Embeddable
public class Author {

    @Column(name = "author_name")
    private String name;
}
