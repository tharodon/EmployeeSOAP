package com.example.employeesoap.exceptions;

public class IllegalAgeException extends RuntimeException{
    public IllegalAgeException() {
        super("Недопустимый возраст для этой позиции");
    }
}
