package utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;


public class CustomClassLoader extends ClassLoader{

    private static final String FOLDER = "Sberteh/classes";

    public CustomClassLoader(ClassLoader parent){
        super(parent);
    }

    private Class getClass(String name) throws ClassNotFoundException {
        byte[] b = null;
        try {
            b = loadClassFileData(name);
            Class c = defineClass(nameTodifineClass(name), b, 0, b.length);
            resolveClass(c);
            return c;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Class loadClass(String name) throws ClassNotFoundException{
        if (name.indexOf(FOLDER) != -1){
            return getClass(name);
        }
        return super.loadClass(name);
    }

    private byte[] loadClassFileData(String name) throws IOException{
        String url = "file:"+name;
        URL myUrl = new URL(url);
        URLConnection connection = myUrl.openConnection();
        InputStream stream = connection.getInputStream();
        int size = stream.available();
        byte buff[] = new byte[size];
        DataInputStream in = new DataInputStream(stream);
        in.readFully(buff);
        in.close();
        return buff;
    }

    public String fileToName(File file){
        return  file.getAbsolutePath();
        }

    private String nameTodifineClass(String name){
        String[] className = name.substring(0, name.length()-6).split(String.valueOf(File.separatorChar));
        return className[className.length-1];
    }
    public static void main(String[] args) throws IOException {
//        CustomClassLoader customClassLoader = new CustomClassLoader(CustomClassLoader.class.getClassLoader());
//        File file = new File("/home/user/JAVA/Sberteh/classes/Employee87546.class");
//        Class clas = null;
//        try {
//            clas = customClassLoader.loadClass(customClassLoader.fileToName(file));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        for (Method m:clas.getDeclaredMethods()){
//            System.out.println(m.getName());
//        }
    }
}

