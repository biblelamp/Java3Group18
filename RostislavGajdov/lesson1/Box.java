package ru.rgajdov.java3.lesson1;

import java.util.ArrayList;

public class Box<E> {

    private float weight;

    private E type;

    private ArrayList<E> fruits;

    public Box(E type, int fruitCount) {
        fruits = new ArrayList<>(fruitCount);

        this.type = type;

        if (type instanceof Apple) {
            for (int i = 0; i < fruitCount; i++) {
                fruits.add((E) new Apple());
            }
            this.weight = new Apple().getWeight() * fruitCount;
        } else if (type instanceof Orange) {
            for (int i = 0; i < fruitCount; i++) {
                fruits.add((E) new Orange());
            }
            this.weight = new Orange().getWeight() * fruitCount;
        }
    }

    float getWeight() {
        return weight;
    }

    boolean compare(Box box) {
        return this.weight == box.weight;
    }

    void move(Box box) {
        if (box.type.getClass().equals(this.type.getClass())) {
            box.addFruits(getFruitsCount());
            fruits.clear();
        } else {
            System.out.println("Нельзя смешивать фрукты!");
        }
    }

    int getFruitsCount() {
        return fruits.size();
    }

    void addFruits(int count) {
        if (type instanceof Apple) {
            for (int i = 0; i < count; i++) {
                fruits.add((E) new Apple());
                weight += (new Apple().getWeight());
            }
        } else if (type instanceof Orange) {
            for (int i = 0; i < count; i++) {
                fruits.add((E) new Orange());
                weight += (new Orange().getWeight());
            }
        }
    }

    public String showType() {
        return "Type of the box: " + type.getClass().getSimpleName();
    }
}
