/**
 * Java 3. Home Work 7.
 *
 * @author Albert Khayrutdinov
 * @version dated Jul 29, 2018
 * @link https://github.com/bertranus
 */
 
import TestClass.*;
import java.lang.reflect.Method;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class J3HW7 {

    public static void main(String[] args) {
        start(J3HW7Test.class);
    }

    @SuppressWarnings("unchecked")
    static void start(Class testClass) {
        List<String> testsList = new ArrayList<String>();
        List<String> beforeList = new ArrayList<String>();
        List<String> afterList = new ArrayList<String>();
        List<String> methodsSequence = new ArrayList<String>();
        try {
            Method[] methods = testClass.getMethods();
            try {
                for (Method o : methods) {
                    if (o.isAnnotationPresent(J3HW7Test.BeforeSuite.class)) {// не осилил динамическую генерацию имени класса тестирования ((
                        beforeList.add(o.getName());
                    } else if (o.isAnnotationPresent(J3HW7Test.Test.class)) {
                        testsList.add(o.getName());
                    } else if(o.isAnnotationPresent(J3HW7Test.AfterSuite.class)) {
                        afterList.add(o.getName());
                    }
                }
                if ((beforeList.size() == 0) || (beforeList.size() >=2) ||
                        (afterList.size() == 0) || (afterList.size() >=2)){
                    throw new RuntimeException();
                }
                methodsSequence.add(beforeList.get(0));
                for (int i = 1; i < 11; i++) {
                    for (int j = 0; j < testsList.size(); j++) {
                        try {
                            Method mm = testClass.getMethod(testsList.get(j));
                            J3HW7Test.Test annotation = mm.getAnnotation(J3HW7Test.Test.class);
                            if (annotation.value() == i) {
                                methodsSequence.add(mm.getName());
                            }
                        }catch (NoSuchMethodException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                methodsSequence.add(afterList.get(0));
            }catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        }catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        // System.out.println(testsList);
        // System.out.println(beforeList);
        // System.out.println(afterList);
        // System.out.println(methodsSequence);
        J3HW7Test cl = new J3HW7Test();
        try {
            for (int k = 0; k < methodsSequence.size(); k++) {
                Method ml = testClass.getMethod(methodsSequence.get(k));
                try {
                    Object oo = ml.invoke(cl);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }

}