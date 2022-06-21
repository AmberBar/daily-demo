package com.amber.limiter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 漏桶算法
 */
public class LeakyRateLimiter implements RateLimiter {
    ArrayBlockingQueue<String> arrayBlockingQueue;

    private int limit;
    private int period;
    private int amount;

    public LeakyRateLimiter(int limit, int period, int amount) {
        this.period = period;
        this.amount = amount;
        this.limit = limit;
        arrayBlockingQueue = new ArrayBlockingQueue<>(limit);
        start();
    }

    public void start() {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {

            take(amount);
        }, 0, period, TimeUnit.MILLISECONDS);
    }

    private void take(int amount) {
        for (int i = 0; i < amount; i++) {
            arrayBlockingQueue.poll();
            System.out.println("take");
        }
    }


    @Override
    public boolean tryAcquire() {
        return arrayBlockingQueue.offer("token");
    }

    public static void main(String[] args) throws InterruptedException {
        LeakyRateLimiter limiter = new LeakyRateLimiter(20, 1000, 5);

        for (int i = 0; i < 200; i++) {
            new Thread(() -> {
                if (limiter.tryAcquire()) {
                    System.out.println("------------------");
                } else {
//                    System.out.println("false");
                }
            }).start();
            Thread.sleep(100);
        }
    }
}
