/* (C)2022 */
package com.example.employeesoap.exception;

import static com.example.employeesoap.support.ConstantsSupport.*;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

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
