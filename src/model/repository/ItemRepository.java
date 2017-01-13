package model.repository;

import model.Connector;
import model.SqlIO;
import model.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        Item item = new Item();
        try {
            ResultSet rs = sqlIO.getResultSet("SELECT * FROM item WHERE id="+id);
            if(rs.next()) {
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public Item[] findAll() {
        return new Item[0];
    }
}
