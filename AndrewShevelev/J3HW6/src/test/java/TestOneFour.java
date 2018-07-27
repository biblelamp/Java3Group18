/**
 * 2. Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной
 * четверки или единицы, то метод вернет false​; Написать набор тестов для этого метода (по 3-4
 * варианта входных данных).
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestOneFour {

    private J3HomeWork6 homeWork;


    @Before
    public void init() {
        homeWork = new J3HomeWork6();
    }

    @Test
    public void testOne () {
        int[] arr = {1, 1, 3, 3, 2, 3, 3, 2, 2};
        Assert.assertFalse("False - not One and Four", homeWork.existOneFour(arr));

    }
    @Test
    public void testTwo () {
        int[] arr = {2, 2, 3, 3, 2, 3, 3, 2, 4};
        Assert.assertFalse("False - not One and Four", homeWork.existOneFour(arr));

    }

    @Test
    public void testThree () {
        int[] arr = {1, 1, 3, 3, 2, 3, 3, 2, 4};
        Assert.assertFalse("False - not One and Four", homeWork.existOneFour(arr));

    }
}
