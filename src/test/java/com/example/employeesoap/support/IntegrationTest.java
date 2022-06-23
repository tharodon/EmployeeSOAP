package com.example.employeesoap.support;

import com.example.employeesoap.support.Containers;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
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

    @BeforeAll
    static void init() {
        Containers.Initializer.start();
    }
}
