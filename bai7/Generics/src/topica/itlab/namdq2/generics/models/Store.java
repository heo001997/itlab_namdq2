package topica.itlab.namdq2.generics.models;


import java.util.List;

public class Store<T> {
    T[] array;
    int size;
    int current;

    public Store() {
        array = (T[]) new Object[5];
    }

    public void add(T item) {
        grow();
        array[size++] = item;
    }

    public void grow() {
        if ((size) >= array.length) {
            T[] temp = (T[]) new Object[array.length + (array.length / 2)];
            for (int i = 0; i < array.length; i++)
                temp[i] = array[i];
            array = temp;
        }

    }

    public T remove(int index) {
        if (index > array.length) {
            System.out.println("undefined index..");
            return null;
        }

        T item = array[index];
        array[index] = null;
        return item;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null)
                result += "" + array[i] + " ";
        }
        return "[" + result + "]";
    }

    public int size() {
        return array.length;
    }

    public boolean hasNext() {
        return current < (size);
    }

    public T next() {
        return (T) array[current++];
    }
}
