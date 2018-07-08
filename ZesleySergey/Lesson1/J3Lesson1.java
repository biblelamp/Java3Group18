import sun.security.util.Length;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Java. Level 2. Lesson 8
 * Simple server for chat
 *
 * @author Sergey Zesley
 * @version 0.3.3 dated July 08, 2018
 */

public class J3Lesson1 {

    public static void main(String[] args) {
	// write your code here
        String[] arr1={"волк","собака","мопс","вельш-корги"};


        //Задание 1
        System.out.println("Состав массива до: "+ Arrays.asList(arr1));
        Task1(arr1,1,3);
        System.out.println("Состав массива после: "+Arrays.asList(arr1));

        //Задание 2
        ArrayList arrayList2=Task2(arr1);
        System.out.println("Состав ArrayList после: "+arrayList2);

        //Задание 3
        Apple[] apples1={new Apple(),new Apple(),new Apple(),new Apple(),new Apple(),new Apple()};
        Box<Apple> box1=new Box<>();
        box1.addFruits(Arrays.asList(apples1));
        System.out.println("Содержимое box1: "+box1);
        System.out.println("Вес box1: "+box1.getWeight());


        Orange[] oranges1={new Orange(),new Orange(),new Orange(),new Orange()};
        Box<Orange> box2=new Box<>();
        box2.addFruits(Arrays.asList(oranges1));
        System.out.println("Содержимое box2: "+box2);
        System.out.println("Вес box2: "+box2.getWeight());

        Orange[] oranges2={new Orange(),new Orange(),new Orange()};
        Box<Orange> box3=new Box<>();
        box3.addFruits(Arrays.asList(oranges2));
        System.out.println("Содержимое box3: "+box3);
        System.out.println("Вес box3: "+box3.getWeight());

        System.out.println("Сравниваем вес box1(яблоки) и box2(апельсины): "+box1.compare(box2));
        System.out.println("Сравниваем вес box2(апельсины) и box3(апельсины): "+box2.compare(box3));

        System.out.println("Пересыпаем из box2 в box3...");
        box2.sendToAnotherBox(box3);
        System.out.println("Содержимое box2: "+box2);
        System.out.println("Вес box2: "+box2.getWeight());
        System.out.println("Содержимое box3: "+box3);
        System.out.println("Вес box3: "+box3.getWeight());

        System.out.println("Добавляем фрукт в box2");
        box2.addFruit(new Orange());
        System.out.println("Содержимое box2: "+box2);
        System.out.println("Вес box2: "+box2.getWeight());





    }

    public static <T> void Task1 (T[] arr,int ind1,int ind2){
        if(ind1<0 || ind1>=arr.length || ind2<0 || ind2>=arr.length) return;
        T val=arr[ind2];
        arr[ind2]=arr[ind1];
        arr[ind1]=val;
    }

    public static <T> ArrayList Task2(T[] arr){
        return new ArrayList<T>(Arrays.asList(arr));
    }




}

class Fruit{
    public float getWeight() {
        return weight;
    }

    protected float weight;

}

class Apple extends Fruit{
    public Apple(){
        weight=1.0f;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                '}';
    }
}

class Orange extends Fruit{
    public Orange(){
        weight=1.5f;
    }

    @Override
    public String toString() {
        return "Orange{" +
                "weight=" + weight +
                '}';
    }
}

class Box<T extends Fruit>{
    private ArrayList<T> fruits=new ArrayList<>();
    public void addFruit(T fruit){
        fruits.add(fruit);
    }

    public void addFruits(List<T> fruits){
        this.fruits.addAll(fruits);
    }

    public float getWeight(){
        float sum1=0;
        for (T frt:fruits) sum1+=frt.getWeight();
        return sum1;
    }

    public boolean compare(Box<?> box2){
        return getWeight()==box2.getWeight();
    }

    public void sendToAnotherBox(Box<T> box2){
        box2.addFruits(fruits);
        fruits.clear();
    }

    @Override
    public String toString() {
        return "Box{" +
                "fruits=" + fruits +
                '}';
    }
}
