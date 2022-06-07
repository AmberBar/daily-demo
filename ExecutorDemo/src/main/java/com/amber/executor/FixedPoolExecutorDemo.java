package com.amber.executor;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class FixedPoolExecutorDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.shutdown();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1));
        threadPoolExecutor.isTerminated();

        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        ExecutorService executorService2 = Executors.newCachedThreadPool();


    }
}
