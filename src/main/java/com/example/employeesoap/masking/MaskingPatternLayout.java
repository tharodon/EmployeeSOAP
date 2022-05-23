package com.example.employeesoap.masking;
//todo нужно использовать другой пакет. Например,  masking
// done

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@Setter
public class MaskingPatternLayout extends PatternLayout {

    private static final Character SEPARATOR = ',';
    private static final Character END_OF_ENTITY_FIELDS = ')';
    private static final Character VALUE = '*';
    private Pattern pattern;

    //todo не используется
    // используется, но не мной
    public void setPatternsProperty(String patternsProperty) {
        if (patternsProperty != null && !patternsProperty.isEmpty()) {
            this.pattern = Pattern.compile(patternsProperty, Pattern.MULTILINE);
        }
    }

    //todo можно сделать проще ?
    // сделать через стрим
    @Override
    public String doLayout(ILoggingEvent event) {
        StringBuilder message = new StringBuilder(super.doLayout(event));
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            for (int group = 1; group <= matcher.groupCount(); group++) {
                if (matcher.group(group) != null) {
                    int valueLength = getValueLength(matcher.end(group), message);
                    for (int i = matcher.end(group); i < valueLength; i++) {
                        message.setCharAt(i, VALUE);
                    }
                }
            }
        }
        return message.toString();
    }

    private int getValueLength(int pos, StringBuilder message) {
        while (pos < message.length() &&
                message.charAt(pos) != SEPARATOR &&
                message.charAt(pos) != END_OF_ENTITY_FIELDS) {
            pos++;
        }
        return pos;
    }

}