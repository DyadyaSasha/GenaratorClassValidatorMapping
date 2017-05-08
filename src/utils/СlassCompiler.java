package utils;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if (isJavaFile(this.file)){
            fileList.add(this.file);
        } else {
            fileList.addAll(reqursiveSearch(this.file));
        }
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, Locale.getDefault(), null);
        String[] compileOptions = new String[]{"-d", DIR};
        Iterable compilationOptions = Arrays.asList(compileOptions);
        Iterable<? extends JavaFileObject> compilationUnits1 = fileManager.getJavaFileObjectsFromFiles(fileList);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, compilationOptions, null, compilationUnits1);
        task.call();
    }

    public ArrayList<File> reqursiveSearch(File folder) {
        ArrayList<File> fileList = new ArrayList();
        customAddList(fileList, folder);
        return  fileList;
    }

    public void customAddList(ArrayList<File> fileList, File folder){
        File[] files = folder.listFiles();
        for (File item : files) {
            if (isJavaFile(item)) {
                fileList.add(item);
            } else if (item.isDirectory()){
                customAddList(fileList, item);
            }
        }
    }

    public boolean isJavaFile(File file){
        Pattern p = Pattern.compile(".+\\.java");
        Matcher m = p.matcher(file.toString());
        return m.matches() && file.isFile();
    }
}
