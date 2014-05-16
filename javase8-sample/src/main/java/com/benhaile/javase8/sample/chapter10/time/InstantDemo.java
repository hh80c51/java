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

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

/**
 *
 * @author benhail
 */
public class InstantDemo {
    public static void main(String[] args) {
        Clock clock=Clock.system(ZoneId.of("Asia/Shanghai"));
        System.out.println(clock);
        Instant instant=Instant.now(clock);
        System.out.println(instant);
    }
    
    public static void compareDays(Instant day1,Instant day2){
        
    }
}
