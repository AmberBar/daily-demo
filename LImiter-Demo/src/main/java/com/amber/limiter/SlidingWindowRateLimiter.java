package com.amber.limiter;

/**
 * 滑动窗口限流算法
 */
public class SlidingWindowRateLimiter implements RateLimiter{
    private final long permitsSecond;

    public SlidingWindowRateLimiter(long permitsSecond) {
        this.permitsSecond = permitsSecond;
    }

    @Override
    public boolean tryAcquire() {
        return false;
    }
}
