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
public class TransformArrTest {

    private int inputArr[];
    private int outputArr[];

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {new int[]{1,2,4,4,2,3,4,1,7}, new int[]{1,7}},
                {new int[]{1,2,4,4,2,3,4}, new int[]{}},
                {new int[]{4,2,3,5,6,7,8}, new int[]{2,3,5,6,7,8}},
                {new int[]{4,2,3,5,6,7,8}, new int[]{2,3,5,6,7,8}}
        });
    }

    public TransformArrTest(int[] inputArr, int[] outputArr) {
        this.inputArr = inputArr;
        this.outputArr = outputArr;
    }

    @Test
    public void transformTest(){
        Assert.assertArrayEquals(outputArr, new Homework6().transformArr(inputArr));
    }
}
