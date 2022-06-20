package com.amber.reference;

import java.util.concurrent.TimeUnit;

/**
 * 强引用
 */
public class ReferenceDemo {
    public static void main(String[] args) {
        Object myObject = new Object();
        System.out.println("-----gc before: "+myObject);

        myObject = null;
        System.gc();
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("-----gc after: "+myObject);
    }
}
