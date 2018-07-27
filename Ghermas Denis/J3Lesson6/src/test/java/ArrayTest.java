import org.junit.Assert; 
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

//@Ignore
public class ArrayTest {
    private ArrayModifer Ar;

    @Before
    public void init() {
        Ar = new ArrayModifer();
    }

    @Test
    public void test1() {
        Assert.assertArrayEquals(new int[]{1,2}, Ar.in(new int[]{1, 4, 5, 6, 7, 8, 4, 1, 2}));
    }

    @Test
    public void test2() {
        int[] a1 = new int[] {1, 4, 5, 6, 7, 8, 3, 1, 2};
        int[] b1 = new int[] {5, 6, 7, 8,3 , 1, 2};
        Assert.assertArrayEquals(b1, Ar.in(a1));
    }

    @Test
    public void testFailed() {
        int[] a1 = new int[] {1, 4, 5, 6, 7, 8, 4 , 1, 2};
        int[] b1 = new int[] {5, 6, 7, 8,3 , 1, 2};
        Assert.assertNotSame(b1, Ar.in(a1));
    }

    @Test
    public void test41() {
        int[] a1 = new int[] {1, 4, 5, 6, 7, 8, 4 , 1, 2};
        Assert.assertEquals(false, Ar.in41(a1));
    }

    @Test
    public void test412() {
        int[] a1 = new int[] {1, 4};
        Assert.assertEquals(true, Ar.in41(a1));
    }

    @Test
    public void test413() {
        int[] a1 = new int[] {1, 4,4,4,4,4,4,4,44,4,4,4,4,4};
        Assert.assertEquals(false, Ar.in41(a1));
    }

    @Test
    public void test414() {
        int[] a1 = new int[] {1,4,4,4,4,4,4,4,1,1,1,4,4,4,4,4};
        Assert.assertEquals(true, Ar.in41(a1));
    }

}