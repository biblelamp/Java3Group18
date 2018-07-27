package Homework_06;
/**
 * Java. Level 3. Homework 6
 * 1. Написать метод, которому в качестве аргумента передается не пустой одномерный
 * целочисленный массив. Метод должен вернуть новый массив, который получен путем
 * вытаскивания из исходного массива элементов, идущих после последней четверки. Входной
 * массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить
 * RuntimeException.
 * Написать набор тестов для этого метода (по 3-4 варианта входных данных).
 * Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
 *
 *  public static int[] myArrayProcess(int[] arr)
 *  public class ArrayTest
 *
 * 2. Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной
 * четверки или единицы, то метод вернет false​; Написать набор тестов для этого метода (по 3-4
 * варианта входных данных).
 *
 * Задание написано неудобоваримо и его можно трактовать двояко.
 * Я буду исходить из того, что имелось ввиду: "Если в массиве есть число отличное от 4 или 1"
 *
 * public static boolean myArrayCheck(int[] arr)
 * public class ArrayTest2
 *
 * @author Mikhail Barinov
 * @version Jul 27, 2018
 */

public class MainHW06 {

    public static boolean myArrayCheck(int[] arr){
        for(int num:arr){
            if (num!=1&&num!=4){
                return false;
            }
        }
        return true;
    }

    public static int[] myArrayProcess(int[] arr){
        int pos = lastFour(arr);
        if(pos==-1){
            throw new RuntimeException("Invalid array");
        } else {
            int[] newArr = new int[arr.length-(pos+1)];
            for(int i=pos+1;i<arr.length;i++){
                newArr[i-(pos+1)]=arr[i];
            }
            return newArr;
        }
    }

    public static int lastFour(int[] arr){
        int pos=-1;
        for(int i=0; i<arr.length;i++){
            if(arr[i]==4){
                pos=i;
            }
        }
        return pos;
    }

    public static void main(String[] args){
        //System.out.println(Arrays.toString(myArrayProcess(new int[]{0,1,4,2,234,2,1,1,5,6,8,9})));
//        System.out.println(myArrayCheck(new int[]{1,4,1,4,6,4,4,4,4,1}));
    }
}
