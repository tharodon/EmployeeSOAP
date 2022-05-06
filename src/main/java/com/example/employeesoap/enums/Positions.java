package com.example.employeesoap.enums;

import com.example.employeesoap.exceptions.InvalidPositionException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Positions {
    JUNIOR("Junior", 18L, 3L, 50_000L, 70_000L),
    MIDDLE("Middle", 21L, 10L, 90_000L, 210_000L),
    SENIOR("Senior", 28L, 15L, 210_000L, 450_000L),
    MANAGER("Manager", 21L, 15L, 70_000L, 150_000L);

    private final String position;
    private final Long minAge;
    private final Long countTasksMax;
    private final Long salaryMin;
    private final Long salaryMax;

    public static Positions getDefine(String define) throws InvalidPositionException {
        if (JUNIOR.getPosition().equals(define)) {
            return JUNIOR;
        } else if (MIDDLE.getPosition().equals(define)) {
            return MIDDLE;
        } else if (SENIOR.getPosition().equals(define)) {
            return SENIOR;
        } else if (MANAGER.getPosition().equals(define)) {
            return MANAGER;
        }
        throw new InvalidPositionException(define);
    }
}
