package com.example.employeesoap.service;

import com.example.employeesoap.api.EmployeeMapper;
import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.entity.Task;
import static com.example.employeesoap.type.Status.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDto employeeToEmployeeDto(Employee employee) {
        return EmployeeDto.builder()
                .uid(employee.getUid())
                .name(employee.getName())
                .surname(employee.getSurname())
                .position(employee.getPosition())
                .salary(employee.getSalary().toString())
                .grade(employee.getGrade())
                .age(employee.getAge().toString())
                .description(employee.getDescription())
                .tasksUID(Arrays.toString(getTasksUIDs(employee.getTasks())))
                .status(SUCCESS)
                .build();
    }

    @Override
    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        return Employee.builder()
                .uid(employeeDto.getUid())
                .name(employeeDto.getName())
                .surname(employeeDto.getSurname())
                .position(employeeDto.getPosition())
                .salary(Long.valueOf(employeeDto.getSalary()))
                .grade(employeeDto.getGrade())
                .age(Long.valueOf(employeeDto.getAge()))
                .description(employeeDto.getDescription())
                .build();
    }

    private Long[] getTasksUIDs(List<Task> tasks){
        return tasks.stream().map(Task::getUid).toArray(Long[]::new);
    }
}
