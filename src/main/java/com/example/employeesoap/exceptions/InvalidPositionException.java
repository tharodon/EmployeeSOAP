package com.example.employeesoap.exceptions;

public class InvalidPositionException extends IllegalArgumentException{ //todo сделай от Exception
    public InvalidPositionException(String exception) {
        super("Invalid position: " + exception);
    }
}
