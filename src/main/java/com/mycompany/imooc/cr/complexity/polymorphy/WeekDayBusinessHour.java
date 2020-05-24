package com.mycompany.imooc.cr.complexity.polymorphy;


import java.time.LocalTime;

public enum WeekDayBusinessHour implements BusinessHour {
    MONDAY(LocalTime.of(10, 0), LocalTime.of(18, 0)),
    TUESDAY(LocalTime.of(10, 10), LocalTime.of(18, 10)),
    WEDNESDAY(LocalTime.of(9, 30), LocalTime.of(18, 30)),
    THURSDAY(LocalTime.of(9, 30), LocalTime.of(18, 30)),
    FRIDAY(LocalTime.of(10, 0), LocalTime.of(18, 0)),
    SATURDAY(LocalTime.of(10, 30), LocalTime.of(20, 30)),
    SUNDAY(LocalTime.of(10, 30), LocalTime.of(20, 30));

    private LocalTime openTime;

    private LocalTime closeTime;

    WeekDayBusinessHour(LocalTime openTime, LocalTime closeTime) {
        this.openTime = openTime;
        this.closeTime = closeTime;
    }


    @Override
    public LocalTime getOpenTime() {
        return openTime;
    }

    @Override
    public LocalTime getCloseTime() {
        return closeTime;
    }
}
