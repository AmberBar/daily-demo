package com.amber.limiter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TokenBuketRateLimiter implements RateLimiter {
    ArrayBlockingQueue<String> arrayBlockingQueue ;
    private int limit;
    private int amount;
    private int period;

    public TokenBuketRateLimiter(int limit, int amount, int period) {
        arrayBlockingQueue = new ArrayBlockingQueue<>(limit);
        this.period = period;
        this.amount = amount;
        this.limit = limit;
        start();
    }

    public void start() {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            addToken(amount);
        }, 0, period, TimeUnit.MILLISECONDS);
    }

    public void addToken(int amount) {
        for (int i = 0; i < amount; i++) {
            arrayBlockingQueue.offer("token");
        }
    }

    @Override
    public boolean tryAcquire() {
        return arrayBlockingQueue.poll() != null;
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBuketRateLimiter tokenBuketRateLimiter = new TokenBuketRateLimiter(20, 5, 3000);

        for (int i = 0; i < 200; i++) {
            new Thread(() -> {
                if (tokenBuketRateLimiter.tryAcquire()) {
                    System.out.println("------------------");
                } else {
                  System.out.println("false");
                }
            }).start();
            Thread.sleep(100);
        }
    }

}
