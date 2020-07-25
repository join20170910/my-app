package com.mycompany.common.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:    //TODO 日期类
 * @Author:         john
 * @CreateDate:     2020/7/13 9:40
 */
public class DateUtils {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String SLASH_DATE_FORMAT = "yyyy/MM/dd";
    public static final String NOT_SLASH_DATE_FORMAT = "yyyyMMdd";
    public static final String CN_DATE_FORMAT = "yyyy'年'MM'月'dd'日'";
    public static final String MONTH_DAY_YEAR_FORMAT = "M/d/yyyy";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String SLASH_DATETIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final String NOT_SLASH_DATETIME_FORMAT = "yyyyMMdd HH:mm:ss";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    private static final ThreadLocal<SimpleDateFormat> DEFAULT_DATE_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> SLASH_DATE_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy/MM/dd");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> NOT_SLASH_DATE_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> CN_DATE_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy'年'MM'月'dd'日'");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> MONTH_DAY_YEAR_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("M/d/yyyy");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> DEFAULT_DATETIME_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> SLASH_DATETIME_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> NOT_SLASH_DATETIME_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> DEFAULT_TIME_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss");
        }
    };

    public DateUtils() {
    }

    public static final Date parseDate(String date) {
        DateFormat[] formatters = new DateFormat[]{(DateFormat)DEFAULT_DATETIME_FORMATTER.get(), (DateFormat)SLASH_DATETIME_FORMATTER.get(), (DateFormat)NOT_SLASH_DATETIME_FORMATTER.get(), (DateFormat)DEFAULT_TIME_FORMATTER.get(), (DateFormat)DEFAULT_DATE_FORMATTER.get(), (DateFormat)SLASH_DATE_FORMATTER.get(), (DateFormat)NOT_SLASH_DATE_FORMATTER.get(), (DateFormat)CN_DATE_FORMATTER.get(), (DateFormat)MONTH_DAY_YEAR_FORMATTER.get()};
        DateFormat[] var2 = formatters;
        int var3 = formatters.length;
        int var4 = 0;

        while(var4 < var3) {
            DateFormat formatter = var2[var4];

            try {
                return formatter.parse(date);
            } catch (ParseException var7) {
                ++var4;
            }
        }

        return null;
    }

}
