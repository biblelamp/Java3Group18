/**
 * Java 3 Home Work 6
 *
 * @author Andrew Shevelev
 * @version jul 27, 2018
 * <p>
 * https://github.com/ShevelevAndrew
 */


public class J3HomeWork6 {
    public int[] arrAfteFour(int[] arr) {
        int lastFour = -1;
        int copyArr[] = new int[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4) lastFour = i;
        }
        if (lastFour != -1) {
            int j = 0;
             copyArr = new int[arr.length-lastFour-1];
            for (int i = lastFour+1; i < arr.length; i++) {
                copyArr[j] = arr[i];
                j++;
            }
        }else
            throw new RuntimeException("There are not four in the array");

        return copyArr;

    }

    public boolean existOneFour(int[] arr) {
        boolean one = false;
        boolean four = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) one = true;
            if (arr[i] == 4) four = true;
        }
        return (!one | !four);
    }
}
