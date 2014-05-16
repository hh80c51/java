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

package com.benhaile.javase8.chapter10.stamplock.counter;

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


import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author benhail
 */
public class CounterTest {
    
    private Counter counter;

    private CyclicBarrier barrier;

    private int threadNum;
    
    public CounterTest(Counter counter, int threadNum) {
        this.counter = counter;
        barrier = new CyclicBarrier(threadNum + 1); //关卡计数=线程数+1
        this.threadNum = threadNum;
    }
     public static void main(String args[]) {
        new CounterTest(new SynchronizeCounter(), 10000).test();
        new CounterTest(new ReentrantLockCounter(), 10000).test();
        new CounterTest(new StampedLockCounter(), 10000).test();   
    }

    public void test() {
        try {
            for (int i = 0; i < threadNum; i++) {
                new TestThread(counter).start();
            }
            long start = System.currentTimeMillis();
            barrier.await(); // 等待所有任务线程创建
            barrier.await(); // 等待所有任务计算完成
            long end = System.currentTimeMillis();
            System.out.println("count value:" + counter.getValue());
            System.out.println("花费时间:" + (end - start) + "毫秒");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    class TestThread extends Thread {
        private Counter counter;

        public TestThread(final Counter counter) {
            this.counter = counter;
        }

        public void run() {
            try {
                barrier.await();
                for (int i = 0; i < 100; i++)
                    counter.getValue();
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
