package Homework_07;

import java.lang.reflect.Method;

public class TestedClass {
    @BeforeSuite
    void myHelloMethod(){
        System.out.println("Hello!");
    }
    @Test
    void myMainMethod1(){
        System.out.println("Do something 1");
    }
    @Test (priorityValue = 10)
    void myMainMethod2(){
        System.out.println("Do something 2");
    }
    void mySilentMethod(){
        System.out.println("This method should not be tested!");
    }
    @AfterSuite
    void myGoodbyeMethod(){
        System.out.println("Bye!");
    }
//    @BeforeSuite
//    public void myFailMethod(){
//        System.out.println("Fail!");
//    }
}
