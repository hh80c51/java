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

import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author benhail
 */
public class LocalDateDemo {

    public static void main(String[] args) {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2013, Month.SEPTEMBER, 12);
        if (date2.isAfter(date1)) {

        }
        String str = date2.toString();  // 2013-09-12
        boolean leap = date1.isLeapYear();
        int monthLen = date1.lengthOfMonth();
        System.out.println(leap);
        System.out.println(monthLen);

        LocalDate date = LocalDate.now();
        date = date.plusMonths(3).minusDays(1);
        date = date.withDayOfMonth(1);
        date = date.withMonth(10);
        date = date.with(Month.OCTOBER);
        System.out.println(date);
    }
}
