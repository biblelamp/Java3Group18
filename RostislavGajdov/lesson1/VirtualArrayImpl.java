package ru.rgajdov.java3.lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class VirtualArrayImpl<E> implements VirtualArray<E> {

    @Override
    public void swapElements(E[] array, int index1, int index2) {
        E temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    @Override
    public ArrayList<E> arrayToArrayList(E[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

    @Override
    public String display(E[] array) {
        return Arrays.toString(array);
    }

}
