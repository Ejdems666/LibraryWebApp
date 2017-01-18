package model.entity;

/**
 * Created by ivoni on 1/12/2017.
 */
public class ItemAttribute implements Entity {

    private int id;
    private int itemId;
    private int attributeId;
    private String data;

    public ItemAttribute(int id, int itemId, int attributeId, String data) {
        this.id = id;
        this.itemId = itemId;
        this.attributeId = attributeId;
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
        return itemId;
    }

    public void setItemId(int item_id) {
        this.itemId = item_id;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
