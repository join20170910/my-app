package com.mycompany.imooc.cr.complexity.polymorphy;

import java.time.LocalTime;

public class Client {

    public void getMondayBusinessHour() {
        LocalTime openTime = WeekDayBusinessHour.MONDAY.getOpenTime();
        LocalTime closeTime = WeekDayBusinessHour.MONDAY.getCloseTime();
    }
}
