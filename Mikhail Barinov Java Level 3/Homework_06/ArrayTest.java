package Homework_06;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ArrayTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1,2,3,4,5}, new int[]{5}},
                {new int[]{1,2,3,4,5,6,7}, new int[]{5,6,7}},
                {new int[]{4,2,3,0,5,6,7}, new int[]{2,3,0,5,6,7}},
                {new int[]{4,2,4,0,5,6,7}, new int[]{0,5,6,7}}
        });
    }
    private int[] arr1;
    private int[] arr2;
    public ArrayTest(int[] arr1, int[] arr2){
        this.arr1=arr1;
        this.arr2=arr2;
    }
    @Test
    public void massTestMyArrayProcess(){
        Assert.assertArrayEquals(arr2,MainHW06.myArrayProcess(arr1));
    }
//    @Test
//    public void testMyArrayProcess() {
//        Assert.assertArrayEquals(new int[]{5,6,7,8}, MainHW06.myArrayProcess(new int[]{1,2,3,4,5,6,7,8}));
//    }
}
