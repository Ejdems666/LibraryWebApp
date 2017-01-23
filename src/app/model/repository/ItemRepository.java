package app.model.repository;

import app.model.Model;
import app.model.entity.Item;
import hyggedb.insert.InsertionExecutor;
import hyggedb.select.Condition;
import hyggedb.select.Selection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by adam on 13/01/2017.
 */

public class ItemRepository extends AbstractRepository<Item> {
    public ItemRepository(Model model) {
        super(model);
    }

    @Override
    public Item getById(int id) {
        Item item = null;
        try {
            Selection selection = new Selection("item");
            selection.where("id=?",id);
            ResultSet rs = model.getSelectionExecutor().getResult(selection);
            item = mapItem(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    private Item mapItem(ResultSet rs) throws SQLException {
        Item item = null;
        if (rs.next()) {
            item = new Item();
            item.setName(rs.getString("name"));
            item.setImg(rs.getString("img"));
            item.setDescription(rs.getString("description"));
            item.setId(rs.getInt("id"));
            item.setUserId(rs.getInt("user_id"));
            item.setCategoryId(rs.getInt("category_id"));
            identityMap.add(item);
        }
        return item;
    }

    @Override
    public Collection<Item> findAll() {
        ArrayList<Item> items = new ArrayList<Item>();
        try {
            ResultSet rs = model.getSelectionExecutor().getResult(new Selection("item"));
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
    public Collection<Item> findBy(Condition condition) {
        ArrayList<Item> items = new ArrayList<Item>();
        try {
            Selection selection = new Selection("item");
            selection.setWhere(condition);
            ResultSet rs = model.getSelectionExecutor().getResult(selection);
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
    public void persist(Item entity) {
        persistedEntities.add(entity);
    }

    @Override
    public void persistAndFlush(Item entity) {
        persist(entity);
        flush();
    }

    @Override
    public void flush() {
        InsertionExecutor insertionExecutor = model.getInsertionExecutor();
        Object[] objects;
        String sql;
        for (Item persistedEntity : persistedEntities) {
            if(!identityMap.contains(persistedEntity)) {
                sql = "INSERT INTO item(name,img,description,category_id,user_id) VALUES(?,?,?,?,?)";
                objects = new Object[5];
                objects[0] = persistedEntity.getName();
                objects[1] = persistedEntity.getImg();
                objects[2] = persistedEntity.getDescription();
                objects[3] = persistedEntity.getCategoryId();
                objects[4] = 1;
                int id = insertionExecutor.insert(sql,objects);
                persistedEntity.setId(id);
            } else {
                sql = "UPDATE item SET name=?,img=?,description=? WHERE id=?";
                objects = new Object[4];
                objects[0] = persistedEntity.getName();
                objects[1] = persistedEntity.getImg();
                objects[2] = persistedEntity.getDescription();
                objects[3] = persistedEntity.getId();
                insertionExecutor.update(sql,objects);
            }
        }
    }
}

