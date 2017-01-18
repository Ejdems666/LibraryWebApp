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

    public int getItemId() {
        return item_id;
    }

    public void setItemId(int item_id) {
        this.item_id = item_id;
    }

    public int getAttributeId() {
        return attribute_id;
    }

    public void setAttributeId(int attribute_id) {
        this.attribute_id = attribute_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
