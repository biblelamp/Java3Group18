/**
 * J3Homework1
 * 1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);
 * 2. Написать метод, который преобразует массив в ArrayList;
 *
 * @author Andrew Shevelev
 * @version Jul 08, 2018
 */
import java.util.ArrayList;
import java.util.Arrays;

public class J3HomeWork1 {

    public static void main(String[] args) {
        J3HomeWork1 hw = new J3HomeWork1();

        Integer[] numbers = {1, 2, 3, 4, 5, 6};
        System.out.println(hw.convertArrayToList(numbers));
        hw.elementsArrayPlaces(0, 2, numbers);
        System.out.println(Arrays.toString(numbers));

    }

    private <T> void elementsArrayPlaces(int ele1, int ele2, T[] arr) {
        T tmp = arr[ele1];
        arr[ele1] = arr[ele2];
        arr[ele2] = tmp;
    }

    private <T> ArrayList<T> convertArrayToList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}
