package utils;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by user on 01.05.17.
 */
public class СlassCompiler {
    private static final String DIR = Paths.get(System.getProperty("user.dir"), "classes").toString();

    private File file;

    public СlassCompiler(File file){
        this.file = file;
    }

    public void classCompile() {
        ArrayList<File> fileList = new ArrayList();
        fileList.add(this.file);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, Locale.getDefault(), null);
        String[] compileOptions = new String[]{"-d", DIR};
        Iterable compilationOptions = Arrays.asList(compileOptions);
        Iterable<? extends JavaFileObject> compilationUnits1 = fileManager.getJavaFileObjectsFromFiles(fileList);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, compilationOptions, null, compilationUnits1);
        task.call();
    }


//    public static void main(String[] args) {
//        String dir = Paths.get(System.getProperty("user.dir"), "classes").toString();
//        ArrayList<File> fileList = new ArrayList();
//        File file = new File("/home/user/JAVA/Sberteh/src/testClasses/Test1.java");
//        fileList.add(file);
//        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, Locale.getDefault(),null);
//        String[] compileOptions = new String[]{"-d", dir};
//        Iterable compilationOptions = Arrays.asList(compileOptions);
//        Iterable<? extends JavaFileObject> compilationUnits1 = fileManager.getJavaFileObjectsFromFiles(fileList);
//        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,null, compilationOptions,null, compilationUnits1);
//        task.call();
//
//    }
}
