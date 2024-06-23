package com.hendisantika.springboottestcontainer.postgresql;

import com.hendisantika.springboottestcontainer.entity.Project;
import com.hendisantika.springboottestcontainer.repository.ProjectRepository;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-testcontainer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/6/23
 * Time: 16:47
 * To change this template use File | Settings | File Templates.
 */
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // deactivate the default behaviour
public class ProjectRepositoryTest {

    @Container
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:17beta1-alpine3.20");
    @Autowired
    private ProjectRepository repository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    public void should_be_able_to_get_project_that_are_already_started_as_at_given_date() {
        //arrange
        Project project1 = new Project(UUID.randomUUID(), "Primary School Attendance", new Date(2022, 12, 15));
        Project project2 = new Project(UUID.randomUUID(), "Pharmacy Inventory System", new Date(2023, 4, 1));
        Project savedProject1 = repository.save(project1);
        Project savedProject2 = repository.save(project2);
        Date today = new Date(2023, 2, 19);
        //act
        List<Project> projects = new ArrayList<>();
        repository.getProjectsThatAreStartedBefore(today).forEach(p -> projects.add(p));
        //assert
        Assertions.assertThat(projects).hasSize(1);
        Assertions.assertThat(projects.get(0).getName()).isEqualTo("Primary School Attendance");
    }
}
