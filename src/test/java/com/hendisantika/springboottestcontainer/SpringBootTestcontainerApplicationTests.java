package com.hendisantika.springboottestcontainer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(
        properties = {
                "management.endpoint.health.show-details=always",
                "spring.datasource.url=jdbc:tc:postgresql:15-alpine:///test"
        },
        webEnvironment = RANDOM_PORT
)
class SpringBootTestcontainerApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Database status will be UP and Database name should be PostgreSQL")
    void databaseIsAvailable() throws com.fasterxml.jackson.core.JsonProcessingException {
        var response = restTemplate.getForEntity("/actuator/health", String.class);

        assertThat(response.getBody()).isNotNull();

        JsonNode root = new ObjectMapper().readTree(response.getBody());
        JsonNode dbComponentNode = root.get("components").get("db");

        String dbStatus = dbComponentNode.get("status").asText();
        String dbName = dbComponentNode.get("details").get("database").asText();

        assertThat(dbStatus).isEqualTo("UP");
        assertThat(dbName).isEqualTo("PostgreSQL");
    }
}
