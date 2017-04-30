package sample.logic;

/**
 * Created by Александра on 30.04.2017.
 */
public class AnnotationAlnalizer {
    public static void main(String[] args){
        try {
            Class c = Class.forName("C:\\Users\\Александра\\Desktop\\project\\src\\testClasses\\Test1.java");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
