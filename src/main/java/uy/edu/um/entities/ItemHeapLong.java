package uy.edu.um.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemHeapLong<T> implements Comparable<ItemHeapLong<T>> {
    private T item;
    private Long comparacion;

    public ItemHeapLong(T itemT, Long comp) {
        item = itemT;
        comparacion = comp;
    }

    @Override
    public int compareTo(ItemHeapLong item) {
        if (comparacion > item.comparacion) {
            return 1;
        } else {
            return -1;
        }
    }
}