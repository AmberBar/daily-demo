# 编写一个自定义类加载器
## 业务逻辑
1. 通过全限定类名找到class文件
2. 把class文件转成Class对象
## 实现逻辑
继承java.lang.ClassLoader类，并且重写findClass方法。
在findClass方法中实现上述业务逻辑
1. 通过全限定类名查找到class文件，并获取文件的byte数组
2. 调用defineClass方法，把class文件转成Class对象，并返回