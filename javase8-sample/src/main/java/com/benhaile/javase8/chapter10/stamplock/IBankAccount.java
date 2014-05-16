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
public interface IBankAccount {
    /**
     * 存钱,需要同步
     * @param amount 
     */
     public void withdraw(long amount);
     /**
      * 取钱
      * @param amount 
      */
     public void deposit(long amount);
     /**
      * 获取当前金额
      * @return 
      */
     public long getBalance();
}
