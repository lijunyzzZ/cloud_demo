package com.demoribbon.dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class NetClassLoader extends ClassLoader {
    private String url;

    public NetClassLoader(String url) {
        this.url = url;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = getClassData(name);
        if (data == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, data, 0, data.length);
        }
    }

    private byte[] getClassData(String name) {
        String path = getNameToPath(name);
        try {
            URL url = new URL(path);
            InputStream ins = url.openStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int read = 0;
            while ((read = ins.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getNameToPath(String className) {
        // 得到类文件的URL
        return url + "/" + className.replace('.', '/') + ".class";
    }

    public static void main(String[] args) throws ClassNotFoundException {
        String rootDir = "C:\\Users\\lijunyi\\IdeaProjects\\bolg\\demo-ribbon\\src\\main\\java";
        //创建自定义文件类加载器
        FileCalssLoader loader = new FileCalssLoader(rootDir);
        FileCalssLoader loader2 = new FileCalssLoader(rootDir);

        try {
            //加载指定的class文件,调用loadClass()
            Class<?> object1 = loader.loadClass("com.demoribbon.dao.DemoObj");
            Class<?> object2 = loader2.loadClass("com.demoribbon.dao.DemoObj");

            System.out.println("loadClass->obj1:" + object1.hashCode());
            System.out.println("loadClass->obj2:" + object2.hashCode());

            //加载指定的class文件,直接调用findClass(),绕过检测机制，创建不同class对象。
            Class<?> object3 = loader.findClass("com.demoribbon.dao.DemoObj");
            Class<?> object4 = loader2.findClass("com.demoribbon.dao.DemoObj");

            System.out.println("loadClass->obj3:" + object3.hashCode());
            System.out.println("loadClass->obj4:" + object4.hashCode());

            /**
             * 输出结果:
             * loadClass->obj1:644117698
             loadClass->obj2:644117698
             findClass->obj3:723074861
             findClass->obj4:895328852
             */

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
