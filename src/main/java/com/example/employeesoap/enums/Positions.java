package com.example.employeesoap.enums;

import com.example.employeesoap.exceptions.InvalidPositionException;

public enum Positions {
    JUNIOR("Junior", 50_000L, 70_000L),
    MIDDLE("Middle", 90_000L, 210_000L),
    SENIOR("Senior", 210_000L, 450_000L),
    MANAGER("Manager", 70_000L, 150_000L);

    private final String position;
    private final Long salaryMin;
    private final Long salaryMax;

    Positions(String position, Long salaryMin, Long salaryMax) {
        this.position = position;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
    }

    public static Positions getDefine(String pos){
        if (Positions.JUNIOR.getPosition().equals(pos)) {
            return JUNIOR;
        } else if (Positions.MIDDLE.getPosition().equals(pos)) {
            return MIDDLE;
        } else if (Positions.SENIOR.getPosition().equals(pos)) {
            return SENIOR;
        } else if (Positions.MANAGER.getPosition().equals(pos)) {
            return MANAGER;
        }
        throw new InvalidPositionException();
    }

    public String getPosition() {
        return position;
    }

    public Long getSalaryMin() {
        return salaryMin;
    }

    public Long getSalaryMax() {
        return salaryMax;
    }
}
