package com.example.employeesoap.exceptions;

public class InvalidPositionException extends Exception { //todo сделай от Exception // done
    public InvalidPositionException(String exception) {
        super("Invalid position: " + exception);
    }
}
