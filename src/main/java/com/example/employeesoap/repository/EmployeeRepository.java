package com.example.employeesoap.repository;

import com.example.employeesoap.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Modifying(flushAutomatically = true)
    @Query(value = "delete from Employee e where e.id = (select min(Employee.id) from Employee)", nativeQuery = true)
    public void deleteByMaxId();
    void deleteById(Long id);
}
