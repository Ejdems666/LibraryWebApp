package model.repository;

import com.mysql.cj.mysqlx.protobuf.MysqlxCrud;
import model.Connector;
import model.QuerySelector;
import model.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by adam on 13/01/2017.
 */
public class ItemRepository implements Repository<Item> {
    private QuerySelector querySelector;
    private ArrayList<Item> identityMap = new ArrayList<>();
    private ArrayList<Item> persistedEntities = new ArrayList<>();

    public ItemRepository(Connector connector) {
        querySelector = new QuerySelector(connector.getConnection());
    }

    @Override
    public Item getById(int id) {
        Item item = null;
        try {
            ResultSet rs = querySelector.getResultSet("SELECT * FROM item WHERE id="+id);
            if(rs.next()) {
                item.setName(rs.getString("name"));
                item.setId(rs.getInt("id"));
                item.setUserId(rs.getInt("user_id"));
                item.setCategoryId(rs.getInt("category_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        identityMap.add(item);
        return item;
    }

    @Override
    public Collection<Item> findAll() {

        ArrayList<Item> items = new ArrayList<Item>();
            try {
                ResultSet rs = querySelector.getResultSet("SELECT * FROM item");
                if(rs.next()) {
                    String name = rs.getString("name");
                    int id = rs.getInt("id");
                    int userId = rs.getInt("user_id");
                    int categoryId = rs.getInt("category_id");
                    items.add(new Item(name, id, userId, categoryId));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return items;
        }

    @Override
    public void persist(Item entity) {
        persistedEntities.add(((Item) entity));
    }

    @Override
    public void persistAndFlush(Item entity) {
        Item[] items;
        String sql;
        int id;
        for (Item persistedEntity : persistedEntities){
            if(!identityMap.contains(persistedEntity)){

            }
        }
    }

    @Override
    public void flush() {

    }

}

