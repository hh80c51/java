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
package com.benhaile.javase8.sample.chapter8.parallelSort;

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


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author benhail
 */
public class ArraysParallelDemo {

    public static void main(String[] args) throws FileNotFoundException {
        for (int i = 1; i < 10; i++) {
            List<Double> arraySource= new ArrayList<>();;
            for (int j = 0; j < i * 10*10000; j++) {
                arraySource.add(new Random().nextDouble());
            }
            sortCompare(arraySource);
        }
    }
    public static void sortCompare(List<Double> arraySource) {
        System.out.println("数组大小："+arraySource.size());
        Double[] myArray = new Double[1];
        myArray = arraySource.toArray(myArray);
        long startTime = System.currentTimeMillis();
        Arrays.sort(myArray);
        long endTime = System.currentTimeMillis();
        System.out.println("线性sort时间: "
                + (endTime - startTime) / 1000.0);

        Double[] myArray2 = new Double[1];
        myArray2 = arraySource.toArray(myArray);
        startTime = System.currentTimeMillis();
        Arrays.parallelSort(myArray2);
        endTime = System.currentTimeMillis();
        System.out.println("并行sort时间: "
                + (endTime - startTime) / 1000.0);
    }
}
