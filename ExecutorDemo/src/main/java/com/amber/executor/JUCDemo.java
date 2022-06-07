package com.amber.executor;

import java.util.concurrent.locks.ReentrantLock;

public class JUCDemo {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock(true);
        reentrantLock.lock();
        reentrantLock.unlock();
    }
}
