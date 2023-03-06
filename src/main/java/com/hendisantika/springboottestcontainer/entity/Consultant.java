package com.hendisantika.springboottestcontainer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-testcontainer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/6/23
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consultant {
    @Id
    private UUID id;
    private String name;
    private int grade;
    private String technology;
}
