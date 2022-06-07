package com.amber;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * 手动编写一个自定义类加载器
 * 1. 需要一个class文件
 */
public class ClassLoaderTest extends ClassLoader{
    private final static String ROOT_PATH = "D:\\amberspace\\daily-demo\\ClassLoaderDemo\\target\\classes\\";
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 通过全限定类名获取文件路径
        String path = ROOT_PATH +"\\" + name.replaceAll("\\.", "\\\\") + ".class";
        byte[] bytes = null;
        try {
             bytes = Files.readAllBytes(new File(path).toPath());


        } catch (IOException e) {
            e.printStackTrace();
        }
        assert bytes != null;
        return  this.defineClass(name, bytes, 0, bytes.length);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoaderTest classLoaderTest = new ClassLoaderTest();
        Class<?> aClass = classLoaderTest.findClass("com.amber.User");
        System.out.println(aClass);
    }
}
