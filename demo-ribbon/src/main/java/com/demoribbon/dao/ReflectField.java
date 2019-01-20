package com.demoribbon.dao;

import java.lang.reflect.Field;

public class ReflectField {
    public static void main(String[] args)throws ClassNotFoundException,NoSuchFieldException,InstantiationException,IllegalAccessException{
        Class<?> clazz = Class.forName("com.demoribbon.dao.Student");
        Field field = clazz.getField("age");
        System.out.println("field:"+field);
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            System.out.println("f:"+ f.getDeclaringClass());
        }
        Field[] fields1 = clazz.getDeclaredFields();
        for (Field f : fields1) {
            System.out.println("f:"+f.getDeclaringClass());
        }
        Field field2 = clazz.getDeclaredField("desc");
        System.out.println("field2:"+field2);
        System.out.println("------------------------------------------------------");
        Student s = (Student) clazz.newInstance();
        Field ageField = clazz.getField("age");
        ageField.set(s,12);
        Field nameField = clazz.getField("name");
        nameField.set(s,"lijuny");
        Field desc = clazz.getDeclaredField("desc");
        desc.set(s,"student");
        Field score = clazz.getDeclaredField("score");
        score.setAccessible(true);
        score.set(s,111);
        s.toString1();
        System.out.println(score.get(s));


    }

}
class Person{
    public int age;
    public String name;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void toString1() {
        System.out.println(age + name);
    }
}

class Student extends Person{
    public String desc;
    private int score;

    public String getDesc() {
        return desc;
    }

    public int getScore() {
        return score;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void toString1() {
        System.out.println(age + "" + name + "" + desc + score);
    }
}
