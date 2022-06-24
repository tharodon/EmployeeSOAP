package com.example.employeesoap.exception;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.example.employeesoap.support.ConstantsSupport.FILENAME;
import static com.example.employeesoap.support.ConstantsSupport.JSON_BUNDLE_KEY;

public class JsonParsingException extends RuntimeException {
    public JsonParsingException(String jsonObj, String message) {
        super(
                MessageFormat.format(
                        ResourceBundle.getBundle(FILENAME, new Locale("US", "US"))
                                .getString(JSON_BUNDLE_KEY),
                        jsonObj,
                        message));
    }
}
