package YakovAlekseev.Lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
//        Задача 1
//        String[] arr1 = {"123","456"};
//        changeElementsInArray(arr1,0,1);
//        System.out.println("Меняем массив местами: \n" + Arrays.toString(arr1));
//        Задача 2
//        ArrayList<String> arrayList = convertArrayToArrayList(arr1);
//        System.out.println("Преобразуем массив в Arraylist:\n" + arrayList.toString());
//        System.out.println(arrayList.getClass());
        //Коробка с яблоками
        Box<Apple> box_apple = new Box<>();
        for (int i = 0; i < 15; i++) {
            box_apple.put(new Apple());
        }
        //Коробка с апельсинами
        Box<Orange> box_orange = new Box<>();
        for (int i = 0; i < 10; i++) {
            box_orange.put(new Orange());
        }

        System.out.println("Сравниваем вес 2х коробок: " + box_apple.compare(box_orange));
    //    box_apple.put(new Orange());  Проверяем, что нельзя в коробас с яблоками положить апельсин
        //3-я коробка для проверки пересыпки
        Box<Orange> box2_orange = new Box<>();
        for (int i = 0; i < 15; i++) {
            box2_orange.put(new Orange());
        }

        System.out.println("Количество в коробке 1: " + box_orange.size());
        System.out.println("Количество в коробке 2: " + box2_orange.size());
        box_orange.pourOutToBox(box2_orange);
        System.out.println("Количество в коробке 2 после пересыпки: " + box2_orange.size());


    }
    //Метод, меняющий элементы массива местами
    public static <T> void changeElementsInArray(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //Метод, преобразующий массив в ArrayList
    public static <T> ArrayList<T> convertArrayToArrayList (T[] arr) {
        ArrayList<T> arrayList = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            arrayList.add(arr[i]);
        }
        return arrayList;
    }
}