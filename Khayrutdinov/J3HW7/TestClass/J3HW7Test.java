/**
 * Java 3. Home Work 7.
 *
 * @author Albert Khayrutdinov
 * @version dated Jul 29, 2018
 * @link https://github.com/bertranus
 */
package TestClass;
import java.lang.reflect.Method;
import java.lang.annotation.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public class J3HW7Test {

    @BeforeSuite
    public void beforeSuiteTest() {System.out.println("This is beforeSuiteTest");}

    @AfterSuite
    public void afterSuiteTest() {System.out.println("This is afterSuiteTest");}

    @Test(value = 1)
    public void firstTest() {System.out.println("This is firstTest");}

    @Test(value = 9)
    public void ninthTest() {System.out.println("This is ninthTest");}

    @Test
    public void noNumberTest() {System.out.println("This is noNumberTest");}


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeforeSuite { }

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AfterSuite { }

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {int value() default 5;}
}