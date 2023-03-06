package com.hendisantika.springboottestcontainer.repository;

import com.hendisantika.springboottestcontainer.entity.Consultant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-testcontainer
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 3/6/23
 * Time: 16:28
 * To change this template use File | Settings | File Templates.
 */
public interface ConsultantRepository extends CrudRepository<Consultant, UUID> {
    @Query(value = "SELECT * FROM Consultant c WHERE c.grade = 2 AND c.technology = :tech", nativeQuery = true)
    List<Consultant> getSeniorConsultantsByTechnology(@Param("tech") String technology);
}
