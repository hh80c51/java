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

package com.benhail.javase8.sample.chapter8.parallelSort;

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
