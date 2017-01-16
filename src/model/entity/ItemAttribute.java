package model.entity;

/**
 * Created by ivoni on 1/12/2017.
 */
public class ItemAttribute implements Entity {

    private int id;
    private int item_id;
    private int attribute_id;
    private String data;

    public ItemAttribute(int id, int item_id, int attribute_id, String data) {
        this.id = id;
        this.item_id = item_id;
        this.attribute_id = attribute_id;
        this.data = data;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(int attribute_id) {
        this.attribute_id = attribute_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
