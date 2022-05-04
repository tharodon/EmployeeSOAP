package com.example.employeesoap.exceptions;

public class IllegalSalaryException extends RuntimeException{ //todo смотреть todo в InvalidPositionException

    public IllegalSalaryException(String s) {
        super(s);
    }
}
