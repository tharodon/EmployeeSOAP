package com.example.employeesoap.exceptions;

public class InvalidPositionException extends RuntimeException { //todo почему RuntimeException ?

    public InvalidPositionException() {
        //todo в коде нельзя оставлять кириллицу. Необходимо использовать ResourceBundle. Посмотреть, как сделать можно в fccr класс MessageService
        super("Такой позиции не существует");
    }
}
