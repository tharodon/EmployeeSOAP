package com.example.employeesoap.exceptions;

import java.text.MessageFormat;

public class JsonParsingException extends RuntimeException{
    public JsonParsingException(String jsonObj, String message) {
        super(MessageFormat.format("Failed json, JsonObject: {0}. Error: {1}", jsonObj, message));
    }
}
