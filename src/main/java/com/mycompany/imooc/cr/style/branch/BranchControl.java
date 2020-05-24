package com.mycompany.imooc.cr.style.branch;

import com.mycompany.imooc.cr.style.constant.DayOfWeek;

public class BranchControl {

    void switchTest(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY:
                // jump to Friday
            case TUESDAY:
                // jump to Friday
            case WEDNESDAY:
                // jump to Friday
            case THURSDAY:
                // jump to Friday
            case FRIDAY:
                System.out.println("weekDay");
                break;
            case SATURDAY:
                // jump to sunday
            case SUNDAY:
                System.out.println("weekend");
                break;
            default:
                System.out.println("error");
        }
    }

    static void stringSwitch(String value) {
        switch (value) {
            case "hello":
                System.out.println("hello");
                break;
            default:
                System.out.println("other string");
        }
    }

    public static void main(String[] args) {
        stringSwitch(null);
    }
}
