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
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockSpaceship implements Spaceship
{
    private final Lock lock = new ReentrantLock();

    private int x;
    private int y;

    @Override
    public int readPosition(final int[] coordinates)
    {
        lock.lock();
        try
        {
            coordinates[0] = x;
            coordinates[1] = y;
        }
        finally
        {
            lock.unlock();
        }

        return 1;
    }

    @Override
    public int move(final int xDelta, final int yDelta)
    {
        lock.lock();
        try
        {
            x += xDelta;
            y += yDelta;
        }
        finally
        {
            lock.unlock();
        }

        return 1;
    }
}
