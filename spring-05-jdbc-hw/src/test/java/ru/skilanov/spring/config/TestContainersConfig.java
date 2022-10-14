package ru.skilanov.spring.config;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        properties = {
                "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///testdb"
        }
)
public class TestContainersConfig {

}
