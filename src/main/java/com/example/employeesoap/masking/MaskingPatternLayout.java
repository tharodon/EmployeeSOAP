package com.example.employeesoap.masking;
//todo нужно использовать другой пакет. Например,  masking
// done

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    // done
    @Override
    public String doLayout(ILoggingEvent event) {
        String message = super.doLayout(event);
        return Arrays.stream(message.split(" "))
                .map(StringBuilder::new)
                .peek(this::maskingMatches).collect(Collectors.joining(" "));
    }

    private void maskingMatches(StringBuilder m) {
        Matcher matcher = pattern.matcher(m);
        if (matcher.find()) {
            int valueLength = getValueLength(matcher.end(), m);
            for (int i = matcher.end(); i < valueLength; i++) {
                m.setCharAt(i, VALUE);
            }
        }
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