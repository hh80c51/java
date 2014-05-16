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

import java.util.concurrent.locks.StampedLock;

/**
 *
 * @author benhail
 */
public class StampedLockCounter implements Counter{

    private volatile long count = 0;

    private final StampedLock sl = new StampedLock();

    @Override
    public long getValue() {
        long stamp = sl.readLock();
            try {
                return count;
            } finally {
                sl.unlockRead(stamp);
        }
    }

    @Override
    public void increment() {
        long stamp = sl.writeLock();
        try {
            count++;
        } finally {
            sl.unlockWrite(stamp);
        }
    }
}
