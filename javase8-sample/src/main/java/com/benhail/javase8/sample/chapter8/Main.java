/*
 * Copyright 2013 benhaile.
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

package com.benhail.javase8.sample.chapter8;

/**
 *
 * @author benhail
 */
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        for (int i=100; i <= 10_000_000; i*=10){
            runTest(i);
        }
    }

    private static void runTest(final int size){

        // Fist obtain two Arrays of same data
        Employee[] empArrForSort = createVeryLargeEmpArray(size);
        Employee[] empArrForSortCopy = Arrays.copyOf(empArrForSort, empArrForSort.length);

        long start = System.currentTimeMillis();
        Arrays.sort(empArrForSort, (e1, e2) -> new Integer(e1.getId()).compareTo(e2.getId()));
        logStart(size + ": sort", start);

        start = System.currentTimeMillis();
        Arrays.parallelSort(empArrForSortCopy, (e1, e2) -> new Integer(e1.getId()).compareTo(e2.getId()));
        logStart(size + ": parallel sort", start);
    }


    private static void logStart(String label, long startTimeMillis) {
        System.out.println("End " + label + " the array. It took: " + (System.currentTimeMillis() - startTimeMillis) + " ms");
    }

    private static Employee[] createVeryLargeEmpArray(final int size) {

        Employee[] ret = new Employee[size];

//        Random rnd=new Random();
        for (int i = 0; i < ret.length; i++) {
//            ret[i] = Employee.createEmployee(rnd.nextInt(ret.length), "Mohammad" + i, "");
            ret[i] = Employee.createEmployee(ret.length - i, "Mohammad" + i, "");
        }

        return ret;
    }

    static class Employee {

        private int id;
        private String name;
        private String email;

        private Employee(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public static Employee createEmployee(int id, String name, String email) {
            return new Employee(id, name, email);
        }

        public int getId() {
            return id;
        }
    }

}
