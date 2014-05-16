/*
 * Copyright 2014 benhaile.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.benhaile.javase8.sample.chapter10.time;

/*
 * #%L
 * javase8-sample
 * %%
 * Copyright (C) 2013 - 2014 benhaile
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */


import java.text.DateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author benhail
 */
public class TimeDemo {

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime :" + localDateTime);

        LocalDate localDate = LocalDate.now();
        System.out.println("localDate :" + localDate);

        LocalTime localtime = LocalTime.now();
        System.out.println("localtime :" + localtime);

//  获取当前年份
        Year year = Year.now();
        System.out.println("year :" + year);
//   从Year获取LocalDate
        LocalDate localDate1 = year.atDay(59);
        System.out.println("localDate1 :" + localDate1);
//  把LocalTime关联到一个LocalDate得到一个LocalDateTime
        LocalDateTime localDateTime1 = localtime.atDate(localDate1);
        System.out.println("localDateTime1 :" + localDateTime1);

//  用指定的年获取一个Year
        Year year1 = Year.of(2012);
        System.out.println("year1 :" + year1);

//  从Year获取YearMonth
        YearMonth yearMonth = year1.atMonth(2);
        System.out.println("yearMonth :" + yearMonth);
//  YearMonth指定日得到 LocalDate
        LocalDate localDate2 = yearMonth.atDay(29);
        System.out.println("localDate2 :" + localDate2);
//  判断是否是闰年
        System.out.println("isLeapYear :" + localDate2.isLeapYear());

//自动处理闰年的2月日期
//创建一个 MonthDay
        MonthDay monthDay = MonthDay.of(2, 29);
        LocalDate leapYear = monthDay.atYear(2012);
        System.out.println("leapYear :" + leapYear);

//同一个 MonthDay 关联到另一个年份上
        LocalDate nonLeapYear = monthDay.atYear(2011);
        System.out.println("nonLeapYear :" + nonLeapYear);

        DayOfWeek dayOfWeek = DayOfWeek.of(1);
        System.out.println("dayOfWeek :" + dayOfWeek);

//计算两个日期之间的天数，还可以按其他时间单位计算两个时间点之间的间隔。
        long between = ChronoUnit.DAYS.between(localDate, leapYear);
        System.out.println("between :" + between);

// 线程安全的格式化类，不用每次都new个SimpleDateFormat
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy MM dd");

//  把日期时间转换为字符串标识
        System.out.println("date formatter :" + dateTimeFormatter.format(nonLeapYear));

//  解析字符串形式的日期时间
        TemporalAccessor temporalAccessor = dateTimeFormatter.parse("2013 01 15");
        System.out.println("temporalAccessor :" + LocalDate.from(temporalAccessor));

        Instant instant = Instant.now(); //  时间戳
        System.out.println("instant :" + instant);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, Calendar.JANUARY, 13);
        DateFormat dateFormat = DateFormat.getDateInstance();
        System.out.printf("Taiwan Java Developer Day is %s.%n",
                dateFormat.format(calendar.getTime()));
        

    }

}
