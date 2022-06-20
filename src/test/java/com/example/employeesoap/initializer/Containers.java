package com.example.employeesoap.initializer;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@UtilityClass
public class Containers {
    public static final PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>("postgres:13.0");

    public static final KafkaContainer kafkaContainer =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgresContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgresContainer.getUsername(),
                    "spring.datasource.password=" + postgresContainer.getPassword(),
                    "spring.flyway.url=" + postgresContainer.getJdbcUrl(),
                    "spring.flyway.user=" + postgresContainer.getUsername(),
                    "spring.flyway.password=" + postgresContainer.getPassword(),
                    "spring.kafka.bootstrap-servers=" + kafkaContainer.getBootstrapServers()
            ).applyTo(applicationContext);
        }

        public static void start() {
            postgresContainer.start();
            kafkaContainer.start();
        }
    }
}
