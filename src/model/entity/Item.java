package model.entity;

/**
 * Created by ivoni on 1/12/2017.
 */
public class Item implements Entity {

    private String name;
    private int id;
    private int userId;
    private int categoryId;

    public Item(String name, int id, int userId, int categoryId) {
        this.name = name;
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
