/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package Beans;

public class CounterManager {
    private int counter = 1; // sesiunea curenta
    private static CounterManager singletonInstance = null;

    public int getCounter() {
        return counter;
    }

    public synchronized void increment(){
        counter+=1;
    }

    public synchronized void decrement(){
        counter-=1;
    }

    public static CounterManager getSingletonInstance(){
        if(singletonInstance == null){
            singletonInstance = new CounterManager();
        }
        return singletonInstance;
    }
}
