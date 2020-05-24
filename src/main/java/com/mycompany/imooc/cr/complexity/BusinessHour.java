package com.mycompany.imooc.cr.complexity;

import java.time.LocalTime;

public class BusinessHour {
    public LocalTime getOpenTime(int weekDay) {
        switch (weekDay) {
            case 1:
                return LocalTime.of(10, 0);
            case 2:
                return LocalTime.of(10, 10);
            case 3:
                return LocalTime.of(9, 30);
            case 4:
                return LocalTime.of(9, 30);
            case 5:
                return LocalTime.of(10, 0);
            case 6:
                return LocalTime.of(10, 30);
            case 7:
                return LocalTime.of(10, 0);
            default:
                throw new RuntimeException("should not execute");
        }
    }

    public LocalTime getCloseTime(int weekDay) {
        switch (weekDay) {
            case 1:
                return LocalTime.of(18, 0);
            case 2:
                return LocalTime.of(18, 10);
            case 3:
                return LocalTime.of(18, 30);
            case 4:
                return LocalTime.of(18, 30);
            case 5:
                return LocalTime.of(18, 0);
            case 6:
                return LocalTime.of(20, 30);
            case 7:
                return LocalTime.of(20, 30);
            default:
                throw new RuntimeException("should not execute");
        }
    }
}
