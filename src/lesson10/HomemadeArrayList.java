package lesson10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class HomemadeArrayList implements HomemadeList {

    private Integer[] array;
    public int capacity = 10;
    private int size;

    public HomemadeArrayList() {
        this.array = new Integer[capacity];
    }

    @Override
    public Integer get(int index) {
        return array[index];
    }

    @Override
    public void set(int index, Integer item) {
        array[index] = item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(String.valueOf(index));
    }

    @Override
    public boolean contains(Integer item) {
        return indexOf(item) >= 0;
    }

    public void extend() {
        Integer[] temp = new Integer[array.length * 2];
        for (int i = 0; i < size; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    @Override
    public Integer[] toArray() {
        return array;
    }

    public void tryToExtend() {
        if (size + 1 >= array.length) extend();
    }

    public void tryToExtend(Integer[] c) {
        if (size + c.length >= array.length) extend();

    }

    @Override
    public void add(Integer item) {
        tryToExtend();
        array[size] = item;
        size++;
    }

    @Override
    public void add(int index, Integer item) {
        rangeCheck(index);
        tryToExtend();
        size++;
        Integer[] temp = Arrays.copyOf(array, size);
        array[index] = item;
        for (int i = index; i < size - index; i++) {
            array[i + 1] = temp[i];
        }
    }

    @Override
    public void remove(Integer item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                remove(indexOf(item));
            }
        }
    }

    @Override
    public void remove(int index) {
        rangeCheck(index);
        set(index, null);
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        set(size, null);
        size--;
    }

    @Override
    public void addAll(Integer[] c) {
        tryToExtend(c);
        for (Integer i : c) {
            add(i);
        }
    }

    @Override
    public void addAll(int index, Integer[] c) {   // [1][2][3][4][5][6]
        tryToExtend(c);
        Integer[] temp = Arrays.copyOfRange(array, index, indexOf(null));
        for (int i = index, i1 = 0; i < index + c.length; i++, i1++) {
            array[i] = c[i1];
            size++;
        }
        for (int i = index + c.length, i1 = 0; i < size; i++, i1++) {
            array[i] = temp[i1];
        }
    }

    @Override
    public void clear() {
        if (size > 100) array = new Integer[10];
        array = new Integer[size];
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(item, array[i])) return i;
        }
        return -80085;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = array.length - 1; i > 0; i--) {
            if (Objects.equals(item, array[i])) return i;
        }
        return -80085;
    }

    @Override
    public void removeAll(Integer[] c) {
        int count = 0;
        int countEnd = 0;
        do {
            count++;
            for (Integer k : c) {
                for (Integer j : array) {
                    if (Objects.equals(j, k)) {
                        remove(indexOf(k));
                        countEnd = count;
                    }
                }
            }
        } while (count <= countEnd);
    }
}