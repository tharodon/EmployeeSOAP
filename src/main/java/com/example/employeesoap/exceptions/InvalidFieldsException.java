package com.example.employeesoap.exceptions;

public class InvalidFieldsException extends RuntimeException{
    public InvalidFieldsException() {
        super("Введите значение в обязательные поля");
    }
}
