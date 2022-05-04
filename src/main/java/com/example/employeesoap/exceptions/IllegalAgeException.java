package com.example.employeesoap.exceptions;

public class IllegalAgeException extends RuntimeException{ //todo смотреть todo в InvalidPositionException
    public IllegalAgeException() {
        super("Недопустимый возраст для этой позиции");
    }
}
