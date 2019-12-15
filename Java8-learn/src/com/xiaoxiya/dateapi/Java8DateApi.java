package com.xiaoxiya.dateapi;

import java.time.*;

/**
 * @author xiaoxiya
 * @date 2019/12/8 16:20
 * @describe java8 日期时间api
 */
public class Java8DateApi {

    public static void main(String[] args) {
        localDateTime();
        testZoneDateTime();
    }

    /**
     * LocalDate/LocalTime和LocalDateTime类可以在处理失去不是必须的情况
     */
    static void localDateTime() {
        //获取当前的日期
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间： " + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();

        System.out.println("月：" + month + ", 日: " + day + ", 秒： " + seconds);

        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2019);
        System.out.println("date2: " + date2);

        //12 december 2019
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        //22小时15分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        //解析字符串
        LocalTime date5 =LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);
    }

    /**
     * 需要考虑当前时区
     */
    static void testZoneDateTime() {

        //获取当前时间日期
        ZonedDateTime date1 =ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当前时区： "+ currentZone);
    }
}
