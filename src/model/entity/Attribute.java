package model.entity;

/**
 * Created by ivoni on 1/12/2017.
 */
public class Attribute implements Entity{

    String name;
    int id;

    public Attribute(String name, int id) {
        this.name = name;
        this.id = id;
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
}
