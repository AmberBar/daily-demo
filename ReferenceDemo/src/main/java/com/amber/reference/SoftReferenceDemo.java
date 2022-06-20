package com.amber.reference;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

public class SoftReferenceDemo {

    /**
     * 设置堆内存 -Xms10m -Xmx10m
     * @param args
     */
    public static void main(String[] args) {
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(softReference);


        try {
            int[] bytes = new int[1024 * 1024 * 9 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("-----gc after内存不够: " + softReference.get());
        }
    }
}
