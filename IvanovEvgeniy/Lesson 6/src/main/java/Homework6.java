/**
 * Java. Level 3. Homework 6.
 *
 * @author Stalker1290
 * @version dated Jul 26,2018
 * @link https://github.com/Stalker1290
 */

import java.util.Arrays;

public class Homework6 {

    public int[] transformArr(int inputArr[]) throws RuntimeException{
        int fourIndex = -1;
        for(int i = 0; i < inputArr.length; i++)
            if(inputArr[i] == 4) fourIndex = i;

        if(fourIndex == -1) throw new RuntimeException("Array don't have any '4' ");
        return Arrays.copyOfRange(inputArr, (fourIndex + 1), inputArr.length);
    }

    public boolean checkArr(int inputArr[]){
        boolean fourFlag = false;
        boolean oneFlag = false;

        for (int element: inputArr) {
            if(element == 4) fourFlag = true;
            else if (element == 1) oneFlag = true;
            else return false;
        }
        return fourFlag && oneFlag;
    }
}
