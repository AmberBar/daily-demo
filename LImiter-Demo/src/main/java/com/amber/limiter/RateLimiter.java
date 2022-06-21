package com.amber.limiter;

public interface RateLimiter {

    boolean tryAcquire();
}
