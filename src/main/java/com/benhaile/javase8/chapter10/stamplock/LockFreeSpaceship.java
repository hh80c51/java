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


import java.util.concurrent.atomic.AtomicReference;

public class LockFreeSpaceship implements Spaceship
{
    private final AtomicReference<Position> position = new AtomicReference<Position>(new Position(0, 0));

    @Override
    public int readPosition(final int[] coordinates)
    {
        final Position currentPosition = position.get();
        coordinates[0] = currentPosition.getX();
        coordinates[1] = currentPosition.getY();

        return 1;
    }

    @Override
    public int move(final int xDelta, final int yDelta)
    {
        int tries = 0;
        Position currentPosition;

        do
        {
            ++tries;
            currentPosition = position.get();
        }
        while (!position.compareAndSet(currentPosition, currentPosition.move(xDelta, yDelta)));

        return tries;
    }

    public static class Position
    {
        private final int x;
        private final int y;

        public Position(final int x, final int y)
        {
            this.x = x;
            this.y = y;
        }

        public int getX()
        {
            return x;
        }

        public int getY()
        {
            return y;
        }

        public Position move(final int xDelta, final int yDelta)
        {
            return new Position(x + xDelta, y + yDelta);
        }
    }
}
