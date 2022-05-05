package com.example.employeesoap.controller;

import com.example.employeesoap.api.EmployeeService;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.entity.ServerResponse;
import com.example.employeesoap.service.DaoProcessing;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final DaoProcessing daoProcessing;

    @PostMapping("/")
    public ResponseEntity<?> addEmployee(@RequestBody List<Employee> employees){
        daoProcessing.addEmployees(employees);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
