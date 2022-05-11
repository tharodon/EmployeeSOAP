package com.example.employeesoap.controller;

import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;
import static com.example.employeesoap.enums.Status.*;
import com.example.employeesoap.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    //todo лучше написать более осмысленный путь
    //done
    @PostMapping("/employee-register")
    public ResponseEntity<?> addEmployee(@RequestBody List<Employee> employees) {
        ///todo добавить логи. Что пришло
        //done
        log.info("request: {}", employees);
        List<EmployeeDto> response = employeeService.addEmployees(employees);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //todo лучше написать более осмысленный путь
    //done
    @PutMapping("/employee")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        log.info("request: {}", employee);
        ///todo добавить логи. Что пришло
        //done
        EmployeeDto response = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //todo лучше написать более осмысленный путь
    //done
    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id) {
        ///todo добавить логи. Что пришло
        //done
        log.info("request: delete by id: {}", id);
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    //todo лучше написать более осмысленный путь
    //done
    @DeleteMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable Long id) {
        ///todo добавить логи. Что пришло
        //done
        log.info("request: delete by id: {}", id);
        employeeService.deleteEmployee(id);
    }
}
