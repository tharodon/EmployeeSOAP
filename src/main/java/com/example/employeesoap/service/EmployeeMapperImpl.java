package com.example.employeesoap.service;

import com.example.employeesoap.api.EmployeeMapper;
import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeMapperImpl implements EmployeeMapper {

    //todo название employeeToEmployeeDto
    //done
    @Override
    public EmployeeDto employeeToEmployeeDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .position(employee.getPosition())
                .salary(employee.getSalary())
                .grade(employee.getGrade())
                .age(employee.getAge())
                .description(employee.getDescription())
                .tasksUID(getTasksUIDs(employee.getTasks()))
                .build();
    }

    private Long[] getTasksUIDs(List<Task> tasks){
        return tasks.stream().map(Task::getUid).toArray(Long[]::new);
    }
}
