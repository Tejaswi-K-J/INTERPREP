// The process of creating the dynamic array using the static array //

import java.util.Iterator;
import java.util.Scanner;

public class DynamicArray<T> implements Iterable<T> {  //DynamicArray<T> is a generic class
    private T[] arr;
    private int len = 0; // length user thinks array is
    private int capacity = 0; // actual array size

    public DynamicArray() {
        this(2); // start with an initial capacity of 2
    }

    public DynamicArray(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size() {
        return len;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        if (index >= len || index < 0) throw new IndexOutOfBoundsException();
        return arr[index];
    }

    public void set(int index, T elem) {
        if (index >= len || index < 0) throw new IndexOutOfBoundsException();
        arr[index] = elem;
    }

    public void add(T elem) {
        if (len == capacity) {
            // Double the capacity and create a new array
            capacity = capacity == 0 ? 1 : capacity * 2;
            T[] newArr = (T[]) new Object[capacity];
            // Copy elements to new array
            for (int i = 0; i < len; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr; // assign newArr to arr
        }
        arr[len++] = elem; // add element and increase the length
    }

    public T removeAt(int rm_index) {
        if (rm_index >= len || rm_index < 0) throw new IndexOutOfBoundsException();
        T data = arr[rm_index];
        T[] newArr = (T[]) new Object[len - 1];
        for (int i = 0, j = 0; i < len; i++, j++) {
            if (i == rm_index) j--; // skip over rm_index by fixing j temporarily
            else newArr[j] = arr[i];
        }
        arr = newArr;
        capacity = --len;
        return data;
    }

    public boolean remove(Object obj) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(obj)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < len; i++) {
            arr[i] = null;
        }
        len = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < len;
            }

            @Override
            public T next() {
                return arr[index++];
            }
        };
    }

    public static void main(String[] args) {
        DynamicArray<Integer> dynArr = new DynamicArray<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter numbers to add to the dynamic array (enter -1 to stop):");
        while (true) {
            int input = scanner.nextInt();
            if (input == -1) break;
            dynArr.add(input);
        }

        System.out.println("Size: " + dynArr.size()); // Output the size of the dynamic array
        System.out.println("Elements in the dynamic array:");
        for (int i : dynArr) {
            System.out.println(i); // Output the elements of the dynamic array
        }
    }
}
