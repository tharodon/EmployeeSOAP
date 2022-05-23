package com.example.employeesoap.controller;

import com.example.employeesoap.api.EmployeeService;
import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.service.pdf.PDFGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PDFGeneratorService pdfGeneratorService;

    @GetMapping("/employee/{id}/pdf")
    public void getPdfFile(@PathVariable String id, HttpServletResponse response){
        log.info("EmployeeController: request: get pdf by id: {}", id);
        pdfGeneratorService.export(response, employeeService.getEmployeeById(id));
    }
    @PostMapping("/employee-register")
    public ResponseEntity<?> addEmployee(@RequestBody List<Employee> employees) {
        log.info("EmployeeController: request: {}", employees);
        List<EmployeeDto> response = employeeService.addEmployees(employees);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/employee")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        log.info("EmployeeController: request: {}", employee);
        EmployeeDto response = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getEmployee(@PathVariable String id) {
        log.info("EmployeeController: request: get by id: {}", id);
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }


    @DeleteMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable String id) {
        log.info("EmployeeController: request: delete by id: {}", id);
        employeeService.deleteEmployee(id);
    }
}
