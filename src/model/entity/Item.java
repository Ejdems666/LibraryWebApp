package model.entity;

/**
 * Created by ivoni on 1/12/2017.
 */
public class Item implements Entity {

    String name;
    int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item(){

    }

    @Override
    public int getId() {
        return 0;
    }
}
