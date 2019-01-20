package com.demoribbon.dao;

import jdk.internal.util.xml.impl.Input;

import java.io.*;

public class FileCalssLoader extends ClassLoader {
    private String rootdir;

    public FileCalssLoader(String rootdir) {
        this.rootdir = rootdir;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = getClassData(name);
        if (data == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, data, 0, data.length);
        }
    }

    private String classNameToPath(String className) {
        return rootdir + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
    }

    private byte[] getClassData(String className) {
        String path = classNameToPath(className);
        try {
            InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesNumRead);
            }
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        String rootDir = "C:\\Users\\lijunyi\\IdeaProjects\\bolg\\demo-ribbon\\src\\main\\java";
        FileCalssLoader fl = new FileCalssLoader(rootDir);
        try {
            Class<?> o1 = fl.loadClass("com.demoribbon.dao.DemoObj");
            System.out.println(o1.newInstance().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
