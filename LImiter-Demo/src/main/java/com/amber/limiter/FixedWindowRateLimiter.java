package com.amber.limiter;

/**
 * 固定窗口限流
 */
public class FixedWindowRateLimiter {
    private final long permitsSecond;
    private long timestamp = System.currentTimeMillis();
    private long count = 0;

    public FixedWindowRateLimiter(long permitsSecond) {
        this.permitsSecond = permitsSecond;
    }

    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        if (now - timestamp <= 1000) {
            return ++count <= permitsSecond;
        }

        timestamp = now;
        count = 0;
        return  false;
    }

    public static void main(String[] args) {
        FixedWindowRateLimiter fixedWindowRateLimiter = new FixedWindowRateLimiter(2);

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(fixedWindowRateLimiter.tryAcquire() + "i = " + finalI);
            }, i + "").start();
        }
    }
}
