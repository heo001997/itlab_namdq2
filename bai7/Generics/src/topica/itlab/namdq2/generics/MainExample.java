package topica.itlab.namdq2.generics;

import topica.itlab.namdq2.generics.models.Store;

public class MainExample {
    public static void main(String[] args) {

        Store<Integer> store = new Store<Integer>();
        store.add(1);
        store.add(2);
        store.add(3);
        store.add(4);
        store.add(5);
        store.add(6);
        System.out.println(store.toString());
        store.remove(5);
        System.out.println(store.toString());
        while (store.hasNext()) {
            System.out.println(store.next());
        }
        System.out.println("size of array " + store.size());
    }
}
