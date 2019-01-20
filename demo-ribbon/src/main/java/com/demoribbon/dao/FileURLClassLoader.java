package com.demoribbon.dao;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

public class FileURLClassLoader extends URLClassLoader {
    public FileURLClassLoader(URL[] urls, ClassLoader parent){
        super(urls,parent);
    }
    public FileURLClassLoader(URL[] urls) {
        super(urls);
    }
    public static void main(String[] args)throws ClassNotFoundException, MalformedURLException {
        String rootDir = "C:\\Users\\lijunyi\\IdeaProjects\\bolg\\demo-ribbon\\src\\main\\java";
        File file = new File(rootDir);
        URI uri = file.toURI();
        URL[] urls = {uri.toURL()};
        FileURLClassLoader fileURLClassLoader = new FileURLClassLoader(urls);

        try {
            Class<?> o = fileURLClassLoader.loadClass("com.demoribbon.dao.DemoObj");
            System.out.println(o.newInstance().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
