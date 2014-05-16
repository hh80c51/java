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


/**
 * Interface to a concurrent representation of a ship that can move around
 * a 2 dimensional space with updates and reads performed concurrently.
 */
public interface Spaceship
{
    /**
     * Read the position of the spaceship into the array of coordinates provided.
     *
     * @param coordinates into which the x and y coordinates should be read.
     * @return the number of attempts made to read the current state.
     */
    int readPosition(final int[] coordinates);

    /**
     * Move the position of the spaceship by a delta to the x and y coordinates.
     *
     * @param xDelta delta by which the spaceship should be moved in the x-axis.
     * @param yDelta delta by which the spaceship should be moved in the y-axis.
     * @return the number of attempts made to write the new coordinates.
     */
    int move(final int xDelta, final int yDelta);
}
