package com.example.employeesoap.repository;

import com.example.employeesoap.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional  //todo зачем поставил аннотацию над всем интерфейсом ?
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Modifying(flushAutomatically = true)
    @Query(value = "delete from Employee e where e.id = (select min(Employee.id) from Employee)", nativeQuery = true)
    public void deleteByMaxId(); //todo public необязателен. Ммм запрос делает не то, что ожидаешь от названия метода

    void deleteById(Long id); //todo у JpaRepository вроде он есть
}
