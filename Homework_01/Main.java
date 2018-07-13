package Homework_01;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
//1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);

    public static void mySwapArrayElements(Object[] arr, int element1, int element2){
        Object tempValue;
        tempValue=arr[element1];
        arr[element1]=arr[element2];
        arr[element2]=tempValue;
    }
//2. Написать метод, который преобразует массив в ArrayList;

    public static ArrayList myToArrayList (Object[] arr){
        ArrayList<Object> myArrayList = new ArrayList<>();
        for(Object o: arr){
            myArrayList.add(o);
        }
        return myArrayList;
    }

    public static void main(String[] args) {

        // Это первые два маленьких задания
    String[] arr1 = new String[]{"ad","asdfasd","ads"};
    Integer[] arr2 = new Integer[]{1,2,3,4,5};
    System.out.println(Arrays.toString(arr1));
    mySwapArrayElements(arr1,0,1);
    System.out.println(Arrays.toString(arr1));
    System.out.println(Arrays.toString(arr2));
    mySwapArrayElements(arr2,0,1);
    System.out.println(Arrays.toString(arr2));
    ArrayList<Object> testList = new ArrayList<>();
    testList = myToArrayList(arr1);
    System.out.println(Arrays.toString(testList.toArray()));
    System.out.println(Arrays.toString(Arrays.asList(arr1).toArray())); // Встроенный метод преобразования Arrays.asList()

//        3. Большая задача:
//        a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
//        b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта,
// поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
//        c. Для хранения фруктов внутри коробки можете использовать ArrayList;
//        d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
// (вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
//        e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
// которую подадут в compare в качестве параметра, true - если их веса равны, false в противном случае
// (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
//        f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку
// (помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами),
// соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
//        g. Не забываем про метод добавления фрукта в коробку.

//        Создаем 3 новых коробки и смотрим что они пустые
        System.out.println("Создаем 3 новых коробки и смотрим что они пустые");
        Box box1 = new Box();
        Box box2 = new Box();
        Box box3 = new Box();
        box1.printBoxInfo();
        box2.printBoxInfo();
        box3.printBoxInfo();
//        Добавляем 15 яблок в первую коробку
        System.out.println("Добавляем 15 яблок в первую коробку");
        for (int i=0;i<15;i++){
            box1.addFruit(new Apple());
        }
        box1.printBoxInfo();
//        Пробуем добавить апельсин в первую коробку
        System.out.println("Пробуем добавить апельсин в первую коробку");
        box1.addFruit(new Orange());
//        Добавляем 10 апельсинов во вторую коробку
        System.out.println("Добавляем 10 апельсинов во вторую коробку");
        for (int i=0;i<10;i++){
            box2.addFruit(new Orange());
        }
        box2.printBoxInfo();
//        Сравниваем первую и и вторую, потом первую и третью коробки
        System.out.println("Сравниваем первую и и вторую, потом первую и третью коробки");
        System.out.println(box1.compare(box2));
        System.out.println(box1.compare(box3));
//        Пересыпаем содержимое первой в третью
        System.out.println("Пересыпаем содержимое первой в третью");
        box1.transfer(box3);
        box1.printBoxInfo();
        box3.printBoxInfo();
//        Пробуем пересыпать вторую в третью
        System.out.println("Пробуем пересыпать вторую в третью");
        box2.transfer(box3);
    }
}