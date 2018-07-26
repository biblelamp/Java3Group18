/**
 * Java. Level 3. Homework 6 Tests.
 *
 * @author Stalker1290
 * @version dated Jul 26,2018
 * @link https://github.com/Stalker1290
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TransformArrExTest {

    private int inputArr[];

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {new int[]{1,2,3,5,6,7}},
                {new int[]{5,20,57,12,9,0}},
        });
    }

    public TransformArrExTest(int[] inputArr) {
        this.inputArr = inputArr;
    }

    @Test (expected = RuntimeException.class)
    public void transformExeptionTest(){
        new Homework6().transformArr(inputArr);
    }
}
