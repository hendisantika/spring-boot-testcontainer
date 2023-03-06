package com.hendisantika.springboottestcontainer.repository;

import com.hendisantika.springboottestcontainer.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    @Query(value = "SELECT * FROM Project p WHERE p.start_date <= :beforeDate", nativeQuery = true)
    List<Project> getProjectsThatAreStartedBefore(@Param("beforeDate") Date beforeDate);
}
