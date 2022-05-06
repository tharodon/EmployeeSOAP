package com.example.employeesoap.controller;

import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.dto.EmployeeErrorResponse;
import com.example.employeesoap.service.DaoProcessing;
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

    private final DaoProcessing daoProcessing;

    @PostMapping("/")
    public ResponseEntity<?> addEmployee(@RequestBody List<Employee> employees) {
        EmployeeErrorResponse response = daoProcessing.addEmployees(employees);
        if (response.hasError()) {
            log.debug("Response with error: {}", response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        EmployeeErrorResponse response = daoProcessing.updateEmployee(employee);
        if (response.hasError()) {
            log.debug("Response with error: {}", response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id) {
        return new ResponseEntity<>(daoProcessing.getEmployeeById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable Long id) {
        daoProcessing.deleteEmployee(id);
    }
}
