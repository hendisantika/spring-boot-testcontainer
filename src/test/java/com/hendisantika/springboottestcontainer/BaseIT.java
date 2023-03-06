package com.hendisantika.springboottestcontainer;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-testcontainer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/7/23
 * Time: 06:03
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class BaseIT {

    static final PostgreSQLContainer<?> postgreSQLContainer;

    static {
        postgreSQLContainer =
                new PostgreSQLContainer<>(DockerImageName.parse("postgres:15-alpine3.17"))
                        .withDatabaseName("test")
                        .withUsername("luffy")
                        .withPassword("s3cret")
                        .withReuse(true);

        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    static void datasourceConfig(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    }
}
