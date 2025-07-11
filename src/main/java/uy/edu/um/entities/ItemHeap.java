package uy.edu.um.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemHeap<T> implements Comparable<ItemHeap<T>> {
    private T item;
    private Integer comparacion;

    public ItemHeap(T itemT, Integer comp) {
        item = itemT;
        comparacion = comp;
    }

    @Override
    public int compareTo(ItemHeap item) {
        return comparacion - item.comparacion;
    }
}

