package utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by user on 03.05.17.
 */
public class CustomClassLoader extends ClassLoader{

    public CustomClassLoader(ClassLoader parent){

        super(parent);
    }

    private Class getClass(String name) throws ClassNotFoundException {
        String file = name;
        byte[] b = null;
        try {
            b = loadClassFileData(file);

            System.out.println("BYTEARRAY: "+b.length);
            file = name.substring(24,name.length()-6).replace(File.separatorChar, '.');
            System.out.println("fileNameTodefineClass: "+file);

            Class c = defineClass(file, b, 0, b.length);
            resolveClass(c);
            return c;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
        public Class loadClass(String name) throws ClassNotFoundException{
            if (name.indexOf("Sberteh/classes") != -1){
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

        public static void main(String[] args) throws IOException {
        CustomClassLoader customClassLoader = new CustomClassLoader(CustomClassLoader.class.getClassLoader());
        File file = new File("/home/user/JAVA/Sberteh/classes/ArrayVector.class");
        Class clas = null;
            try {
                clas = customClassLoader.loadClass(customClassLoader.fileToName(file));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(clas.getName());
        }
}

