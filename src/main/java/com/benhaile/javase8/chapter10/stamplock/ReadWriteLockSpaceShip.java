package com.benhaile.javase8.chapter10.stamplock;

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


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockSpaceShip implements Spaceship
{
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    private int x;
    private int y;

    @Override
    public int readPosition(final int[] coordinates)
    {
        readLock.lock();
        try
        {
            coordinates[0] = x;
            coordinates[1] = y;
        }
        finally
        {
            readLock.unlock();
        }

        return 1;
    }

    @Override
    public int move(final int xDelta, final int yDelta)
    {
        writeLock.lock();
        try
        {
            x += xDelta;
            y += yDelta;
        }
        finally
        {
            writeLock.unlock();
        }

        return 1;
    }
}
