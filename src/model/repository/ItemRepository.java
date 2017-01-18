package model.repository;

import model.Connector;
import model.QuerySelector;
import model.entity.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by adam on 13/01/2017.
 */
public class ItemRepository extends AbstractRepository<Item> {
    public ItemRepository(Connection connection) {
        super(connection);
    }

    @Override
    public Item getById(int id) {
        Item item = null;
        try {
            ResultSet rs = querySelector.getResultSet("SELECT * FROM item WHERE id=" + id);
            item = mapItem(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        identityMap.add(item);
        return item;
    }

    private Item mapItem(ResultSet rs) throws SQLException {
        Item item = null;
        if (rs.next()) {
            item.setName(rs.getString("name"));
            item.setId(rs.getInt("id"));
            item.setUserId(rs.getInt("user_id"));
            item.setCategoryId(rs.getInt("category_id"));
        }
        return item;
    }

    @Override
    public Collection<Item> findAll() {
        ArrayList<Item> items = new ArrayList<Item>();
        try {
            ResultSet rs = querySelector.getResultSet("SELECT * FROM item");
            Item item = mapItem(rs);
            while (item != null) {
                items.add(item);
                item = mapItem(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public Collection<Item> findBy(String[] conditions, Object[] attributes) {
        return null;
    }

    @Override
    public void persist(Item entity) {
        persistedEntities.add(entity);
    }

    @Override
    public void persistAndFlush(Item entity) {
        Item[] items;
        String sql;
        int id;
        for (Item persistedEntity : persistedEntities) {
            if (!identityMap.contains(persistedEntity)) {

            }
        }
    }

    @Override
    public void flush() {

    }

}

