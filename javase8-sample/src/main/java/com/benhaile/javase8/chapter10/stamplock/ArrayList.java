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

package com.benhaile.javase8.chapter10.stamplock;

/**
 *
 * @author benhail
 */
import java.util.Arrays;
 
public class ArrayList<E> {
    private Object[] elems;
    private int next;
 
    public ArrayList(int capacity) {
        elems = new Object[capacity];
    }
 
    public ArrayList() {
        this(16);
    }
 
    public void add(E e) {
        if(next == elems.length) {
            elems = Arrays.copyOf(elems, elems.length * 2);
        }
        elems[next++] = e;
    }
 
    public E get(int index) {
        return (E) elems[index];
    }
 
    public int size() {
        return next;
    }
}
