package com.demoribbon.dao;

import java.lang.reflect.Array;

public class ReflectArray {
    public static void main(String[] args)throws ClassNotFoundException{
        int[] arr = {1,2,2,3,4};
        Class<?> clazz = arr.getClass().getComponentType();
        Object arr1 = Array.newInstance(clazz,15);
        int len = Array.getLength(arr);
        System.arraycopy(arr,0,arr1,0,len);
        for(int item : (int[])arr1){
            System.out.println(item+",");
        }
        Class<?> clazz2 = Class.forName("java.lang.String");
        Object arr2 = Array.newInstance(clazz2,10);
        Array.set(arr2,6,"hello");
        String str = (String) Array.get(arr2,6);
        System.out.println(str);
    }
}
