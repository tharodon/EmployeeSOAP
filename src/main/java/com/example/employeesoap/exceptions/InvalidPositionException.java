package com.example.employeesoap.exceptions;

public class InvalidPositionException extends IllegalArgumentException{
    public InvalidPositionException(String exception) {
        super("Invalid position: " + exception);
    }
}
