/**
 * Java. Level 3. Homework 1.
 *
 * @author Stalker1290
 * @version dated Jul 11,2018
 * @link https://github.com/Stalker1290
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Homework1 {
    public static void main(String[] args) {

        Integer intArr[] = {1,2,3,4,5,6,7,8,9,10};
        System.out.println("Before swap 2 and 4:" + convertArrayToList(intArr));
        swapElements(2,4,intArr);
        System.out.println("After swap 2 and 4:" + convertArrayToList(intArr));

        String strArr[] = {"One","Two","Three","Four","Five","Six","Seven"};
        System.out.println("Before swap 2 and 4:" + convertArrayToList(strArr));
        swapElements(2,4,strArr);
        System.out.println("After swap 2 and 4:" + convertArrayToList(strArr));
    }

    public static <T> void swapElements(int index1, int index2, T[] arr){
        T tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static <T> ArrayList<T> convertArrayToList(T[] arr){
        return new ArrayList<>(Arrays.asList(arr));
    }

}
