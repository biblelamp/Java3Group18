/**
 * Java 3. Home Work 6.
 *
 * @author Albert Khayrutdinov
 * @version dated Jul 27, 2018
 * @link https://github.com/bertranus
 */

import java.util.Arrays;

public class J3HW612 {
    public static void main(String[] args) {
    }

    public int [] method1(int [] inArray) {
        int count = 0;
        for (int i = inArray.length-1; i > -2; i--) {
            if (inArray[i] == 4) {
                count = inArray.length - i - 1;
                break;
            }
        }
        int [] outArray = new int [count];
        System.arraycopy(inArray, inArray.length - count, outArray, 0, count);
        return outArray;
    }

    public boolean method2(int [] inArray) {
        if ((inArray.length < 1) ||
            (Arrays.stream(inArray).min().getAsInt() != 1) ||
            (Arrays.stream(inArray).max().getAsInt() != 4) ||
             (Arrays.toString(inArray).contains("2")) ||
             Arrays.toString(inArray).contains("3"))
        return false;
        return true;
    }
}
