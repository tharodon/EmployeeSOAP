package com.example.employeesoap.support.testdata;

import com.example.employeesoap.dto.UserDto;

public class Constants {

    public static final String USERNAME = "GoogleMan";

    public static final String USERNAME_DUPLICATE = "mamba";

    public static final String EMAIL = "123@mail.ru";

    public static final String EMAIL_DUPLICATE = "1@mail.ru";

    public static final String PASSWORD = "myPassword123";

    public static UserDto getValidUserDto(){
        return UserDto.builder()
                .username(USERNAME)
                .roles(null)
                .email(EMAIL)
                .password(PASSWORD)
                .build();
    }

    public static UserDto getUserDtoWithUsernameDuplicate(){
        return UserDto.builder()
                .username(USERNAME_DUPLICATE)
                .roles(null)
                .email(EMAIL)
                .password(PASSWORD)
                .build();
    }

    public static  UserDto getUserDtoWithEmailDuplicate(){
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
