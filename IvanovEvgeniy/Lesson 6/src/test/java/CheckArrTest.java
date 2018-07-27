/**
 * Java. Level 3. Homework 6 Tests.
 *
 * @author Stalker1290
 * @version dated Jul 26,2018
 * @link https://github.com/Stalker1290
 */

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CheckArrTest {

    private int inputArr[];
    private boolean output;

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {new int[]{1,1,1,1,1,1}, false},
                {new int[]{4,4,4,4,4,4}, false},
                {new int[]{1,4,4,1,2,1}, false},
                {new int[]{1,1,1,1,1,4}, true},
                {new int[]{4,4,4,4,4,1}, true},
        });
    }

    public CheckArrTest(int[] inputArr, boolean output) {
        this.inputArr = inputArr;
        this.output = output;
    }

    @Test
    public void checkArrayTest(){
        Assert.assertEquals(output, new Homework6().checkArr(inputArr));
    }
}
