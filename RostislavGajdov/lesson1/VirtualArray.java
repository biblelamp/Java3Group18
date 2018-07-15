package ru.rgajdov.java3.lesson1;

import java.util.ArrayList;

public interface VirtualArray<E> {
    void swapElements(E[] array, int index1, int index2);
    ArrayList<E> arrayToArrayList(E[] array);
    String display(E[] array);
}
