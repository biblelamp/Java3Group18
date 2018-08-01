package Homework_07;

import java.lang.reflect.Method;

public class Tester {
    public static void start(Class c) {
        Method[] methods = c.getDeclaredMethods();
        boolean isFirst = true;
        try {
            Object myInstance = c.newInstance();
            for (Method o : methods) {
                if ((o.getAnnotation(BeforeSuite.class) != null)&&isFirst) {
                    System.out.println(o);
                    o.invoke(myInstance);
                    isFirst=false;
                } else if((o.getAnnotation(BeforeSuite.class) != null)&&(isFirst==false)){
                    throw new RuntimeException("Only one @BeforeSuite method allowed!");
                }
            }
            for (Method o : methods) {
                if ((o.getAnnotation(Test.class) != null)&& o.getAnnotation(Test.class).priorityValue()==10) {
                    System.out.println(o);
                    o.invoke(myInstance);
                }
            }
            for (Method o : methods) {
                if ((o.getAnnotation(Test.class) != null)&& o.getAnnotation(Test.class).priorityValue()==9) {
                    System.out.println(o);
                    o.invoke(myInstance);
                }
            }
            for (Method o : methods) {
                if ((o.getAnnotation(Test.class) != null)&& o.getAnnotation(Test.class).priorityValue()==8) {
                    System.out.println(o);
                    o.invoke(myInstance);
                }
            }
            for (Method o : methods) {
                if ((o.getAnnotation(Test.class) != null)&& o.getAnnotation(Test.class).priorityValue()==7) {
                    System.out.println(o);
                    o.invoke(myInstance);
                }
            }
            for (Method o : methods) {
                if ((o.getAnnotation(Test.class) != null)&& o.getAnnotation(Test.class).priorityValue()==6) {
                    System.out.println(o);
                    o.invoke(myInstance);
                }
            }
            for (Method o : methods) {
                if ((o.getAnnotation(Test.class) != null)&& o.getAnnotation(Test.class).priorityValue()==5) {
                    System.out.println(o);
                    o.invoke(myInstance);
                }
            }
            for (Method o : methods) {
                if ((o.getAnnotation(Test.class) != null)&& o.getAnnotation(Test.class).priorityValue()==4) {
                    System.out.println(o);
                    o.invoke(myInstance);
                }
            }
            for (Method o : methods) {
                if ((o.getAnnotation(Test.class) != null)&& o.getAnnotation(Test.class).priorityValue()==3) {
                    System.out.println(o);
                    o.invoke(myInstance);
                }
            }
            for (Method o : methods) {
                if ((o.getAnnotation(Test.class) != null)&& o.getAnnotation(Test.class).priorityValue()==2) {
                    System.out.println(o);
                    o.invoke(myInstance);
                }
            }
            for (Method o : methods) {
                if ((o.getAnnotation(Test.class) != null)&& o.getAnnotation(Test.class).priorityValue()==1) {
                    System.out.println(o);
                    o.invoke(myInstance);
                }
            }
            isFirst=true;
            for (Method o : methods) {
                if ((o.getAnnotation(AfterSuite.class) != null)&&isFirst) {
                    System.out.println(o);
                    o.invoke(myInstance);
                    isFirst=false;
                } else if((o.getAnnotation(AfterSuite.class) != null)&&(isFirst==false)){
                    throw new RuntimeException("Only one @AfterSuite method allowed!");
                }
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
