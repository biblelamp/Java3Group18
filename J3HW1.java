/**
 * Java 3. Home Work 1.
 *
 * @author Albert Khayrutdinov
 * @version dated Jul 08, 2018
 * @link https://github.com/bertranus
 */

import java.util.Arrays;
import java.util.ArrayList;

class TestGeneric<T> {
    private T obj;

    public TestGeneric(T оbj) {
        this.obj = оbj;
    }

    public String toString(){
        return "Type of obj: " + obj.getClass().getName() + " value = " + obj;
    }
}

public class J3HW1 {
    public static void main(String args[]) {
        TestGeneric[] testArray = {new TestGeneric<String>("Hello"), new TestGeneric<Integer>(2018), new TestGeneric<Float>(11.1f)};
        System.out.println(Arrays.toString(testArray));

        System.out.println(Arrays.toString(arrayChange(testArray, 0,2)));

        System.out.println(toList(testArray));
    }
    
    static TestGeneric[] arrayChange(TestGeneric[] testArray, int num1, int num2) {
        TestGeneric temp = testArray[num1];
        testArray[num1] = testArray[num2];
        testArray[num2] = temp;
        return testArray;
    }

    static ArrayList toList(TestGeneric[] testArray) {
        return new ArrayList<>(Arrays.asList(testArray));

    }
}