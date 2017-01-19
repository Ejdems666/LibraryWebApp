package app.model.repository;

import app.model.entity.Review;
import hyggedb.insert.InsertionExecutor;
import hyggedb.select.Condition;
import hyggedb.select.Selection;
import app.model.Model;
import app.model.entity.ItemAttribute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ivoni on 1/15/2017.
 */
public class ItemAttributeRepository extends AbstractRepository<ItemAttribute> {

    public ItemAttributeRepository(Model model) {
        super(model);
    }

    @Override
    public ItemAttribute getById(int id) {
        ItemAttribute itemAttribute = null;
        try {
            Selection selection = new Selection("item_atribute");
            selection.where("id=?",id);
            ResultSet rs = model.getSelectionExecutor().getResult(selection);
            itemAttribute = mapItemAttribute(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemAttribute;
    }

    private ItemAttribute mapItemAttribute(ResultSet rs) throws SQLException {
        ItemAttribute itemAttribute = null;
        if (rs.next()) {
            itemAttribute = new ItemAttribute();
            itemAttribute.setId(rs.getInt("id"));
            itemAttribute.setItemId(rs.getInt("item_id"));
            itemAttribute.setItemId(rs.getInt("attribute_id"));
            itemAttribute.setData(rs.getString("data"));
            identityMap.add(itemAttribute);
        }
        return itemAttribute;
    }

    @Override
    public Collection<ItemAttribute> findAll() {
        ArrayList<ItemAttribute> itemAttributes = new ArrayList<ItemAttribute>();

        try {
            ResultSet rs = model.getSelectionExecutor().getResult(new Selection("item_atribute"));
            ItemAttribute itemAttribute = mapItemAttribute(rs);
            while (itemAttribute != null) {
                itemAttributes.add(itemAttribute);
                itemAttribute = mapItemAttribute(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemAttributes;

    }

    @Override
    public Collection<ItemAttribute> findBy(Condition condition) {
        return null;
    }

    @Override
    public void persist(ItemAttribute entity) {
        persistedEntities.add(entity);
    }

    @Override
    public void persistAndFlush(ItemAttribute entity) {
        persist(entity);
        flush();
    }

    @Override
    public void flush() {
        InsertionExecutor insertionExecutor = model.getInsertionExecutor();
        Object[] objects;
        String sql;
        for (ItemAttribute persistedEntity : persistedEntities) {
            if(!identityMap.contains(persistedEntity)) {
                sql = "INSERT INTO item_attribute(item_id,attribute_id,data) VALUES(?,?,?)";
                objects = new Object[3];
                objects[0] = persistedEntity.getItemId();
                objects[1] = persistedEntity.getAttributeId();
                objects[2] = persistedEntity.getData();
                int id = insertionExecutor.insert(sql,objects);
                persistedEntity.setId(id);
            } else {
                sql = "UPDATE item_attribute SET item_id=?,attribute_id=?,data=? WHERE id=?";
                objects = new Object[4];
                objects[0] = persistedEntity.getItemId();
                objects[1] = persistedEntity.getAttributeId();
                objects[2] = persistedEntity.getData();
                objects[4] = persistedEntity.getId();
                insertionExecutor.update(sql,objects);
            }
        }
    }
}
