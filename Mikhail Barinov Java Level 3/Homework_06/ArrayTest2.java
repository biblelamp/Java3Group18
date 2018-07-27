package Homework_06;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ArrayTest2 {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1,2,3,4,5}, false},
                {new int[]{1,4,1,4,6,4,4,4,4,1}, false},
                {new int[]{1,4,1,4,1,4,4,4,4,1}, true},
                {new int[]{4}, true}
        });
    }
    private int[] arr1;
    private boolean result;
    public ArrayTest2(int[] arr1, boolean result){
        this.arr1=arr1;
        this.result=result;
    }
    @Test
    public void massTestMyArrayProcess(){
//        Assert.assertFalse(MainHW06.myArrayCheck(arr1));
        Assert.assertEquals(result,MainHW06.myArrayCheck(arr1));
    }
}
