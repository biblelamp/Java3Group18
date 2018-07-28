
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Java. Level 3. Lesson 7
 *
 * @author Sergey Zesley
 * @version 0.0.1 dated July 28, 2018
 * @link https://github.com/SergeyZes/Java3Group18
 */

public class J3Lesson7 {

    public static void main(String[] args) {
        TestingClass.start(TestingClass.class);
    }
}


class TestingClass{
    public static void start(Class cls){
        if (cls!=TestingClass.class){
            System.out.println("Тестирование прервано.");
            System.out.println("Вы должны были передать объект типа \"TestingClass.class\".");
            return;
        }
        performTest(cls);
    }
    public static void start(String clsName){
        Class cls=null;
        try {
            cls=Class.forName(clsName);
            if (cls!=TestingClass.class){
                System.out.println("Тестирование прервано.");
                System.out.println("Вы должны были передать объект типа \"TestingClass.class\".");
                return;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        performTest(cls);
    }
    private static void performTest(Class cls) throws RuntimeException
    {
        TestingClass testingObj=null;
        try {
            testingObj=(TestingClass)cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Method[] methods=cls.getMethods();

        Method beforeMethod=null;
        Method afterMethod=null;
        List<MethNPrior> testingMethods=new ArrayList<>();

        for (Method method: methods){

            if (method.getAnnotation(BeforeSuite.class)!=null){
                if(beforeMethod!=null) throw new RuntimeException("Метод с аннотацией @BeforeSuite должен быть один.");
                beforeMethod=method;
            } else
            if (method.getAnnotation(AfterSuite.class)!=null){
                if(afterMethod!=null) throw new RuntimeException("Метод с аннотацией @AfterSuite должен быть один.");
                afterMethod=method;
            } else
            if (method.getAnnotation(Test.class)!=null){
                Test annotationTst=method.getAnnotation(Test.class);
                testingMethods.add(new MethNPrior(method,annotationTst.value()));
            }
        }

        try {
            if (beforeMethod != null) {
                beforeMethod.invoke(testingObj);
            }

            testingMethods.sort(Comparator.comparing(MethNPrior::getPriority));

            for (MethNPrior methNPrior:testingMethods) {
                methNPrior.method.invoke(testingObj);
            }

            if (afterMethod!=null){
                afterMethod.invoke(testingObj);
            }
        } catch (Exception e){
            e.printStackTrace();
            return;
        }


    }

    @Test(value = 5)
    public void Test5(){
        System.out.println("Выполняется Test5.");
    }
    @Test(value = 2)
    public void Test2(){
        System.out.println("Выполняется Test2.");
    }
    @AfterSuite
    public void AfterAllMeth(){
        System.out.println("Данный метод выполняется после всех.");
    }

    @BeforeSuite
    public void BeforeAllMeth(){
        System.out.println("Данный метод выполняется перед всеми.");
    }

    @Test(value = 1)
    public void Test1(){
        System.out.println("Выполняется Test1.");
    }


}

class MethNPrior{
    public Method method;
    public Integer priority;

    public Integer getPriority() {
        return priority;
    }


    public MethNPrior(Method method, int priority) {
        this.method = method;
        this.priority = priority;
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BeforeSuite{
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface AfterSuite{
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Test{
    int value() default 5;
}
