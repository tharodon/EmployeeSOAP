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
                .id(employee.getId() == null ? null : employee.getId().toString())
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

    private Long[] getTasksUIDs(List<Task> tasks){
        return tasks.stream().map(Task::getUid).toArray(Long[]::new);
    }
}
