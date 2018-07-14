/**
 * Java 3. Lesson 1. Homework
 *
 * @author Rostislav Gajdov / Ростислав Гайдов
 * @version dated Jul 14, 2018
 * @link https://github.com/rgajdov
 */
package ru.rgajdov.java3.lesson1;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Integer[] arr1 = { 1, 3, 5, 2, 4 };
        VirtualArrayImpl<Integer> va1 = new VirtualArrayImpl<>();
        System.out.println("Исходный массив = " + va1.display(arr1));
        va1.swapElements(arr1, 2, 3);
        System.out.println("Измененный массив = " + va1.display(arr1));

        String[] arr2 = { "line1", "line2", "line3" };
        VirtualArrayImpl<String> va2 = new VirtualArrayImpl<>();
        System.out.println("Исходный массив = " + va2.display(arr2));
        va2.swapElements(arr2, 0, 1);
        System.out.println("Измененный массив = " + va2.display(arr2));

        System.out.println("============================");

        System.out.println("Массив Integer, преобразованный в ArrayList: " + va1.arrayToArrayList(arr1));
        System.out.println("Массив String, преобразованный в ArrayList: " + va2.arrayToArrayList(arr2));



        Box<Apple> appleBox = new Box<>(new Apple(), 100);
        Box<Orange> orangeBox = new Box<>(new Orange(), 150);

        System.out.println("Вес коробки с яблоками: " + appleBox.getWeight());
        System.out.println("Вес коробки с апельсинами: " + orangeBox.getWeight());
        appleBox.addFruits(10);
        appleBox.addFruits(7);
        orangeBox.addFruits(5);
        System.out.println("Количество апельсинов в ящике: " + orangeBox.getFruitsCount());
        System.out.println("Количество яблок в ящике: " + appleBox.getFruitsCount());
        System.out.println("Вес коробки с апельсинами: " + orangeBox.getWeight());
        System.out.println("Вес коробки с яблоками: " + appleBox.getWeight());

        System.out.println("Вес коробок одинаковый? " + appleBox.compare(orangeBox));

        Box<Apple> appleBox1 = new Box<>(new Apple(), 0);
        appleBox.move(appleBox1);
        System.out.println("Количество яблок в ящике applebox: " + appleBox.getFruitsCount());
        System.out.println("Количество яблок в ящике applebox1: " + appleBox1.getFruitsCount());

    }



}
