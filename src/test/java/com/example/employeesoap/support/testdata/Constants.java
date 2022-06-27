package com.example.employeesoap.support.testdata;

import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.dto.UserDto;
import com.example.employeesoap.entity.Employee;

import java.util.Arrays;

import static com.example.employeesoap.type.Status.SUCCESS;

public class Constants {

    public static final String USERNAME = "GoogleMan";

    public static final String USERNAME_DUPLICATE = "mamba";

    public static final String EMAIL = "123@mail.ru";

    public static final String EMAIL_DUPLICATE = "1@mail.ru";

    public static final String PASSWORD = "myPassword123";

    public static final String VICTOR_UID = "1";
    public static final String OLEG_UID = "2";
    public static final String KARL_UID = "3";
    public static final String ANNA_UID = "4";

    public static final EmployeeDto EMPLOYEE_DTO_ANNA =
            EmployeeDto.builder()
                    .uid("4")
                    .position("Manager")
                    .age("44")
                    .name("Anna")
                    .surname("Volatilisina")
                    .salary("75000")
                    .status(SUCCESS)
                    .tasksUID(Arrays.toString(new Long[0]))
                    .build();

    public static final Integer COUNT_OF_EMPLOYEES = 4;

    public static final EmployeeDto LEGAL_EMPLOYEE_2 =
            EmployeeDto.builder()
                    .uid("4")
                    .position("Manager")
                    .age("44")
                    .name("Anna")
                    .surname("Volatilisina")
                    .salary("75000")
                    .status(SUCCESS)
                    .tasksUID(Arrays.toString(new Long[0]))
                    .build();

    public static final Employee LEGAL_EMPLOYEE =
            Employee.builder()
                    .position("Junior")
                    .age(19L)
                    .name("Pavel")
                    .surname("Matronus")
                    .salary(65_000L)
                    .build();
    public static final Employee ILLEGAL_EMPLOYEE =
            Employee.builder()
                    .position("Manager")
                    .age(17L)
                    .name("Ivan")
                    .surname("Pupkin")
                    .salary(65_000L)
                    .build();

    public static Employee getLegalJunior() {
        return Employee.builder()
                .position("Junior")
                .age(19L)
                .name("Vasiliy")
                .surname("Matronus")
                .salary(65_000L)
                .build();
    }

    public static Employee getLegalMiddle() {
        return Employee.builder()
                .position("Middle")
                .age(25L)
                .name("Vasiliy")
                .surname("Matronus")
                .salary(170_000L)
                .build();
    }

    public static Employee getLegalManager() {
        return Employee.builder()
                .position("Manager")
                .age(25L)
                .name("Vasiliy")
                .surname("Matronus")
                .salary(140_000L)
                .grade("first")
                .build();
    }

    public static Employee getLegalSenior() {
        return Employee.builder()
                .position("Senior")
                .age(31L)
                .name("Vasiliy")
                .surname("Matronus")
                .salary(410_000L)
                .grade("first")
                .description("test")
                .build();
    }

    public static UserDto getValidUserDto() {
        return UserDto.builder()
                .username(USERNAME)
                .roles(null)
                .email(EMAIL)
                .password(PASSWORD)
                .build();
    }

    public static UserDto getUserDtoWithUsernameDuplicate() {
        return UserDto.builder()
                .username(USERNAME_DUPLICATE)
                .roles(null)
                .email(EMAIL)
                .password(PASSWORD)
                .build();
    }

    public static UserDto getUserDtoWithEmailDuplicate() {
        return UserDto.builder()
                .username(USERNAME)
                .roles(null)
                .email(EMAIL_DUPLICATE)
                .password(PASSWORD)
                .build();
    }


//    ShouldGetError
//    ShouldNotExists
}
