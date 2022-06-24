package com.example.employeesoap.support;

import com.example.employeesoap.repository.EmployeeRepository;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@Sql(scripts = {
        "/sql/insert_employees.sql",
        "/sql/insert_roles.sql",
        "/sql/insert_users.sql",
})
@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(initializers = {
        Containers.Initializer.class
})
@Transactional
public abstract class IntegrationTest {

//    private final EmployeeRepository employeeRepository;
//
//    @Autowired
//    public IntegrationTest(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }

//    @Autowired
//    private Flyway flyway;

    @BeforeAll
    static void init() {
        Containers.Initializer.start();
    }
}
