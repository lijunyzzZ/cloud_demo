package com.demoribbon.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectMethod {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName("com.demoribbon.dao.Circle");
        Method method = clazz.getMethod("draw", int.class, String.class);
        System.out.println("method" + method);
        Method[] ms = clazz.getMethods();
        for (Method m : ms) {
            System.out.println("method" + m);
        }
        System.out.println("=========================================");
        Method method1 = clazz.getDeclaredMethod("drawCircle");
        System.out.println("method" + method);
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println("m:" + m);
        }

        System.out.println("=========================================");
        Circle circle = (Circle)clazz.newInstance();
        method.invoke(circle,13,"draw");
        Method priM = clazz.getDeclaredMethod("drawCircle");
        priM.setAccessible(true);
        priM.invoke(circle);
        Method returnT = clazz.getDeclaredMethod("getAllCount");
        System.out.println(returnT.invoke(circle));

    }
}

class Shape {
    public void draw() {
        System.out.println("draw");
    }

    public void draw(int count, String name) {
        System.out.println("draw " + name + ",count=" + count);
    }

}

class Circle extends Shape {

    private void drawCircle() {
        System.out.println("drawCircle");
    }

    public int getAllCount() {
        return 100;
    }
}