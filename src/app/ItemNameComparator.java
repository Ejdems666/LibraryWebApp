package app;

import app.model.entity.Item;

import java.util.Comparator;

/**
 * Created by adam on 20/01/2017.
 */
public class ItemNameComparator implements Comparator<Item> {
    @Override
    public int compare(Item o1, Item o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
