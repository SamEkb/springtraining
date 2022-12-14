package ru.skilanov.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.skilanov.spring.config.AppConfig;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class QuestionTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuestionTestApplication.class, args);
	}
}
