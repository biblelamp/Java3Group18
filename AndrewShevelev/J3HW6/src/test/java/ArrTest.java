/**
 * 1. Написать метод, которому в качестве аргумента передается не пустой одномерный
 * целочисленный массив. Метод должен вернуть новый массив, который получен путем
 * вытаскивания из исходного массива элементов, идущих после последней четверки. Входной
 * массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить
 * RuntimeException.
 * Написать набор тестов для этого метода (по 3-4 варианта входных данных).
 * Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ]
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrTest {
    private J3HomeWork6 homeWork;


    @Before
    public void init() {
        homeWork = new J3HomeWork6();
    }

    @Test
    public void testOne () {
        int[] arrOut = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        int[] arrIn = {1,7};
        Assert.assertArrayEquals(arrIn, homeWork.arrAfteFour(arrOut));
    }
    @Test
    public void testTwo () {
        int[] arrOut = {1, 2, 4, 4, 2, 3, 1, 1, 7};
        int[] arrIn = {2, 3, 1, 1, 7};
        Assert.assertArrayEquals(arrIn, homeWork.arrAfteFour(arrOut));
    }
    @Test
    public void testThree () {
            int[] arrOut = {1, 2, 5, 6, 2, 3, 1, 1, 7};
            int[] arrIn = {1, 2, 5, 6, 2, 3, 1, 1, 7};
            Assert.assertArrayEquals(arrIn, homeWork.arrAfteFour(arrOut));
    }
    @Test
    public void testFour () {
        int[] arrOut = {1, 2, 4, 1, 2, 3, 1, 1, 7};
        int[] arrIn = {2, 3, 1, 1, 7};
        Assert.assertArrayEquals(arrIn, homeWork.arrAfteFour(arrOut));
    }

}
