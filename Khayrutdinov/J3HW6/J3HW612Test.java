/**
 * Java 3. Home Work 6.
 *
 * @author Albert Khayrutdinov
 * @version dated Jul 27, 2018
 * @link https://github.com/bertranus
 */

import org.junit.*;

public class J3HW612Test {
    private J3HW612 testClass;
    private int [][] cc = {
            {1,2,3,4},{},
            {1, 2, 4, 4, 2, 3, 4, 1, 7},{1, 7},
            {9,5,3,2},{},
            {4,1,1,4,4,1},
            {1,1,1,1}
    };

    @Before
    public void init() {
        testClass = new J3HW612();
        }

    @Test
    public void test1Method1() {
        Assert.assertArrayEquals(cc[1], testClass.method1(cc[0]));
        }

    @Test
    public void test2Method1() {
        Assert.assertArrayEquals(cc[3], testClass.method1(cc[2]));
    }

    @Test
    public void test3Method1() throws ArrayIndexOutOfBoundsException{
        try {
            Assert.assertArrayEquals(cc[5], testClass.method1(cc[4]));
            Assert.fail("Expected ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException th) {
            Assert.assertNotEquals("", th.getMessage());
        }
    }

    @Test
    public void test1Method2() {
        Assert.assertTrue(!testClass.method2(cc[2]));
    }

    @Test
    public void test2Method2() {
        Assert.assertTrue(!testClass.method2(cc[1]));
    }

    @Test
    public void test3Method2() {
        Assert.assertTrue(testClass.method2(cc[6]));
    }

    @Test
    public void test4Method2() {
        Assert.assertTrue(!testClass.method2(cc[7]));
    }
}


