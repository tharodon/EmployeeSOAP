package com.example.employeesoap.controller;

import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.dto.EmployeeErrorResponse;
import com.example.employeesoap.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/") //todo лучше написать более осмысленный путь
    public ResponseEntity<?> addEmployee(@RequestBody List<Employee> employees) {
        ///todo добавить логи. Что пришло
        //done
        log.info("request: {}", employees);
        EmployeeErrorResponse response = employeeService.addEmployees(employees);
        if (response.hasError()) {
            log.debug("Response with error: {}", response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/") //todo лучше написать более осмысленный путь
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        log.info("request: {}", employee);
        ///todo добавить логи. Что пришло
        //done
        EmployeeErrorResponse response = employeeService.updateEmployee(employee);
        if (response.hasError()) {
            log.debug("Response with error: {}", response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}") //todo лучше написать более осмысленный путь
    public ResponseEntity<?> getEmployee(@PathVariable Long id) {
        ///todo добавить логи. Что пришло
        //done
        log.info("request: delete by id: {}", id);
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}") //todo лучше написать более осмысленный путь
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable Long id) {
        ///todo добавить логи. Что пришло
        //done
        log.info("request: delete by id: {}", id);
        employeeService.deleteEmployee(id);
    }
}
