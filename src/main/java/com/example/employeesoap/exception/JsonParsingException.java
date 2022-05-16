package com.example.employeesoap.exception;

import java.text.MessageFormat;

public class JsonParsingException extends RuntimeException{
    public JsonParsingException(String jsonObj, String message) {
        super(MessageFormat.format("Failed parsing json, JsonObject: {0}. Error: {1}", jsonObj, message));
    }
}
