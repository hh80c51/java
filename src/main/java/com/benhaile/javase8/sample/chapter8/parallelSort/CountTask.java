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


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author benhail
 */
public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD=2;
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }
    
    
    @Override
    protected Integer compute() {
        int sum=0;
        boolean canComput=(end-start)<=THRESHOLD;
        if(canComput){
            for(int i=start;i<=end;i++){
                sum+=i;
            }
        }else{
            int middle=(start+end)/2;
            CountTask left=new CountTask(start,middle   );
            CountTask right=new CountTask(middle,end    );
            left.fork();
            right.fork();
            int leftRet=left.join();
            int rightRet=right.join();
            sum=leftRet+rightRet;
        }
        return sum;
    }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ForkJoinPool forkjoin=new ForkJoinPool();
        CountTask countTask=new CountTask(1,99);
        Future<Integer> result=forkjoin.submit(countTask);
        System.out.println(result.get());
    }
}
