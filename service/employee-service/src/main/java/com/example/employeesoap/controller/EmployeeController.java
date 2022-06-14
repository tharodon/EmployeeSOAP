package com.example.employeesoap.controller;

import com.example.employeesoap.api.EmployeeService;
import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.service.pdf.PDFGeneratorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Контроллер управления персоналом")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PDFGeneratorService pdfGeneratorService;

    @Operation(summary = "Получение pdf-файла с информацией о работнике по uid")
    @GetMapping("/employee/{id}/pdf")
    public void getPdfFile(
            @PathVariable @Parameter(description = "id пользователя", required = true) String id,
            HttpServletResponse response){
        log.info("EmployeeController: request: get pdf by id: {}", id);
        pdfGeneratorService.export(response, employeeService.getEmployeeById(id));
    }
    @Operation(summary = "Регистрация новых работников")
    @PostMapping("/employee-register")
    public ResponseEntity<?> addEmployee(@RequestBody List<Employee> employees) {
        log.info("EmployeeController: request: {}", employees);
        List<EmployeeDto> response = employeeService.addEmployees(employees);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Изменение данных о работнике")
    @PutMapping("/employee")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        log.info("EmployeeController: request: {}", employee);
        EmployeeDto response = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Operation(summary = "Получение информации о работнике по uid")
    @GetMapping("/employee/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getEmployee(
            @PathVariable @Parameter(description = "id пользователя", required = true) String id) {
        log.info("EmployeeController: request: get by id: {}", id);
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @Operation(summary = "Удаление работника по uid")
    @DeleteMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable @Parameter(description = "id пользователя", required = true) String id) {
        log.info("EmployeeController: request: delete by id: {}", id);
        employeeService.deleteEmployee(id);
    }
}
