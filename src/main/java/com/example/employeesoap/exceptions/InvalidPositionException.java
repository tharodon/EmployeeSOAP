package com.example.employeesoap.exceptions;

public class InvalidPositionException extends Exception {
    public InvalidPositionException(String exception) {
        super("Invalid position: " + exception);
    }
}
