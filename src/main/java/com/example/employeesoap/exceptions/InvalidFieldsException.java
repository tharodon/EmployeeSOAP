package com.example.employeesoap.exceptions;

public class InvalidFieldsException extends RuntimeException{ //todo смотреть todo в InvalidPositionException
    public InvalidFieldsException() {
        super("Введите значение в обязательные поля");
    }
}
