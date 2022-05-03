package com.example.employeesoap.exceptions;

public class InvalidPositionException extends RuntimeException{

    public InvalidPositionException() {
        super("Такой позиции не существует");
    }
}
