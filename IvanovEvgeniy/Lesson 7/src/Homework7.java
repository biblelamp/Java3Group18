/**
 * Java. Level 3. Homework 7.
 *
 * @author Stalker1290
 * @version dated Jul 30,2018
 * @link https://github.com/Stalker1290
 */

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Homework7 {

    public static void main(String[] args) {
        TestingUnit.start(TestingUnit.class);
    }
}

class TestingUnit{
    public static void start(Class classInstance){
        startTesting(classInstance);
    }

    public static void start(String className) throws ClassNotFoundException{
        startTesting(Class.forName(className));
    }

    private static void startTesting(Class classInstance) throws RuntimeException{
        Object testObj = null;
        Method beforeSuiteMet = null;
        Method afterSuiteMet = null;
        List<List<Method>> testMethodsList = new ArrayList<List<Method>>(10);

        try{ testObj = classInstance.newInstance(); }
        catch (Exception ex){ ex.printStackTrace(); }

        for(int i = 0; i < 10; i++) testMethodsList.add(new ArrayList<>());

        Method[] methods = classInstance.getDeclaredMethods();
        for (Method o : methods) {
            if (o.getAnnotation(BeforeSuite.class) != null ) {
                if(beforeSuiteMet != null) throw new RuntimeException("Must be one BeforeSuite method");
                beforeSuiteMet = o;
            }
            if (o.getAnnotation(AfterSuite.class) != null ) {
                if(afterSuiteMet != null) throw new RuntimeException("Must be one AfterSuite method");
                afterSuiteMet = o;
            }
            if (o.getAnnotation(Test.class) != null ) {
                int priority = o.getAnnotation(Test.class).priority();
                if((priority > 0) && (priority <= 10) ) testMethodsList.get(priority - 1).add(o);
            }
        }

        try{
            beforeSuiteMet.invoke(testObj);
            for (List<Method> methodsList: testMethodsList) {
                for (Method testMethod: methodsList) {
                    testMethod.invoke(testObj);
                }
            }
            afterSuiteMet.invoke(testObj);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before Suite Method");
    }

    @Test
    public void testDefaultPrior(){
        System.out.println("Test with default prior");
    }

    @Test(priority = 1)
    public void testPrior1(){
        System.out.println("Test with 1 prior");
    }

    @Test(priority = 10)
    public void testPrior10(){
        System.out.println("Test with 10 prior");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("Before Suite Method");
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BeforeSuite {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface AfterSuite {
}

@Retention (RetentionPolicy.RUNTIME)
@Target (ElementType.METHOD)
@interface Test {
    int priority() default 5;
}