package com.amber;

import java.io.*;
import java.nio.file.Files;

public class UserDefineClassLoader extends ClassLoader {
    private static final String ROOT_PATH = "D:\\amberspace\\ClassLoaderDemo\\target\\classes\\";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 通过全限定类名获取.class文件
        String filePath = findFilePathByName(name);
        byte[] classByFile = getClassByFile(filePath);
        if (null != classByFile) {
            return this.defineClass(name, classByFile, 0, classByFile.length);
        }
        throw new ClassNotFoundException();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        UserDefineClassLoader userDefineClassLoader = new UserDefineClassLoader();
        Class<?> aClass = userDefineClassLoader.findClass("com.amber.User");
        System.out.println(aClass.getName());
        UserDefineClassLoader userDefineClassLoader2 = new UserDefineClassLoader();
        Class<?> aClass2= userDefineClassLoader2.findClass("com.amber.User");
        // 不同类加载器加载同一个类，生成的Class文件是不一样的。同理可得一个类可以被多次加载（类加载器需要不同）
        System.out.println(aClass == aClass2);
    }

    private byte[] getClassByFile(String filePath) {

        try {
            return Files.readAllBytes(new File(filePath).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private String findFilePathByName(String name) {
        return ROOT_PATH + name.replaceAll("\\.", "\\\\") + ".class";
    }
}
