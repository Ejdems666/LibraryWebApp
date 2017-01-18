package model.repository;

import model.Connector;
import model.SqlIO;
import model.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by adam on 13/01/2017.
 */
public class ItemRepository implements Repository<Item> {
    private SqlIO sqlIO;

    public ItemRepository(Connector connector) {
        sqlIO = new SqlIO(connector.getConnection());
    }

    @Override
    public Item getById(int id) {
        Item item = null;
        try {
            ResultSet rs = sqlIO.getResultSet("SELECT * FROM item WHERE id="+id);
            if(rs.next()) {
                item.setName(rs.getString("name"));
                item.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public Collection<Item> findAll() {

        ArrayList<Item> items = new ArrayList<Item>();
            try {
                ResultSet rs = sqlIO.getResultSet("SELECT * FROM item");
                if(rs.next()) {
                    String name = rs.getString("name");
                    int id = rs.getInt("id");
                    items.add(new Item(name, id));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return items;
        }

    @Override
    public void persist(Item entity) {

    }

    @Override
    public void persistAndFlush(Item entity) {

    }

    @Override
    public void flush() {

    }

}

