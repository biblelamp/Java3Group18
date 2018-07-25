import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * Java. Level 3. Lesson 6
 *
 * @author Sergey Zesley
 * @version 0.0.1 dated July 25, 2018
 * @link https://github.com/SergeyZes/Java3Group18
 */


public class J3Lesson6Test {
    public static J3Lesson6 j3Lesson6;
    private int lastid=-1;


    @BeforeClass
    public static void beforeTests(){
        j3Lesson6=new J3Lesson6();
    }

    @AfterClass
    public static void afterTests(){
        j3Lesson6.closeConnection();
        j3Lesson6=null;
    }

    @Test
    public void test1_getArrayAfter4(){
        Assert.assertArrayEquals(new int[]{2,6,1,9},j3Lesson6.getArrayAfter4(new int[]{8,7,4,5,4,2,6,1,9}));

    }
    @Test
    public void test2_getArrayAfter4(){
        Assert.assertArrayEquals(new int[]{8,1,2,0},j3Lesson6.getArrayAfter4(new int[]{3,5,14,16,4,8,1,2,0}));

    }
    @Test
    public void test3_getArrayAfter4(){
        Assert.assertArrayEquals(new int[]{12,26,1,3},j3Lesson6.getArrayAfter4(new int[]{17,2,15,4,12,26,1,3}));

    }

    @Test
    public void test1_check1_4inArray(){
        Assert.assertTrue(j3Lesson6.check1_4inArray(new int[]{1,4,1,1,4}));
    }

    @Test
    public void test2_check1_4inArray(){
        Assert.assertFalse(j3Lesson6.check1_4inArray(new int[]{1,1,1}));
    }

    @Test
    public void test3_check1_4inArray(){
        Assert.assertFalse(j3Lesson6.check1_4inArray(new int[]{4,4,4}));
    }

    @Test
    public void test_add_student(){
        lastid=j3Lesson6.insertStudent("Петров",5);
        Assert.assertTrue(lastid>=0);
        j3Lesson6.deleteStudent(lastid);
    }

    @Test
    public void test_read_student(){
        lastid=j3Lesson6.insertStudent("Иванов",5);
        Assert.assertEquals("Иванов",j3Lesson6.readStudent(lastid));
        j3Lesson6.deleteStudent(lastid);
    }

    @Test
    public void test_update_student(){
        lastid=j3Lesson6.insertStudent("Васечкин",5);
        j3Lesson6.updateStudent(lastid,"Павлов",6);
        Assert.assertEquals("Павлов",j3Lesson6.readStudent(lastid));
        j3Lesson6.deleteStudent(lastid);
    }


}
