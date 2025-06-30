package uy.edu.um.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemHeapDouble<T> implements Comparable<ItemHeapDouble<T>> {
    private T item;
    private Double comparacion;

    public ItemHeapDouble(T itemT, Double comp) {
        item = itemT;
        comparacion = comp;
    }

    @Override
    public int compareTo(ItemHeapDouble item) {
        if (comparacion > item.comparacion) {
            return 1;
        } else {
            return -1;
        }
    }
}