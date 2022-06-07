package com.amber.executor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 5, 5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                (x) -> {
                    return new Thread(x, "name-" + atomicInteger.incrementAndGet());
                }
                , new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 1000; i++) {
            try {
                threadPoolExecutor.execute(() -> {

                    System.out.println("hahah" + Thread.currentThread().getName());

                });
            } catch (RejectedExecutionException e) {
                System.err.println("error");
            }
        }

    }
}
