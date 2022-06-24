/* (C)2022 */
package com.example.employeesoap.repository;

import com.example.employeesoap.entity.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUid(String uid);

    @Transactional
    void deleteByUid(String uid);
}
