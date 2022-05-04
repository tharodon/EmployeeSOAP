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

    //todo параметр не сокращай + название лучше сделать define
    public static Positions getDefine(String pos){
        //todo можно сделать импорт констант и избавиться от Positions. Это облегчит читаемость кода
        if (Positions.JUNIOR.getPosition().equals(pos)) {
            return JUNIOR;
        } else if (Positions.MIDDLE.getPosition().equals(pos)) {
            return MIDDLE;
        } else if (Positions.SENIOR.getPosition().equals(pos)) {
            return SENIOR;
        } else if (Positions.MANAGER.getPosition().equals(pos)) {
            return MANAGER;
        }
        //todo пробросить pos ?
        throw new InvalidPositionException();
    }
    //todo лучше использовать lombok))
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
