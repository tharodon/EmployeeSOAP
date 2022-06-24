package com.example.employeesoap.repository;

import com.example.employeesoap.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUid(String uid);

    @Transactional
    void deleteByUid(String uid);
}
