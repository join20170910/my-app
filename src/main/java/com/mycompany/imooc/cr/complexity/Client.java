package com.mycompany.imooc.cr.complexity;


import java.time.LocalTime;

public class Client {

    public void getMondayBusinessHour() {
        BusinessHour businessHour = new BusinessHour();
        LocalTime openTime = businessHour.getOpenTime(1);
        LocalTime closeTime = businessHour.getCloseTime(1);
    }

}
