package model.repository;

import model.entity.ItemAttribute;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ivoni on 1/15/2017.
 */
public class ItemAttributeRepository extends AbstractRepository<ItemAttribute> {

    public ItemAttributeRepository(Connection connection) {
        super(connection);
    }

    @Override
    public ItemAttribute getById(int id) {
        ItemAttribute itemAttribute = null;
        try {
            ResultSet rs = querySelector.getResultSet("SELECT * FROM itemAtribute WHERE id = " + id);
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
            ResultSet rs = querySelector.getResultSet("SELECT * FROM itemAtribute");
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
    public Collection<ItemAttribute> findBy(String[] conditions, Object[] attributes) {
        return null;
    }

    @Override
    public void persist(ItemAttribute entity) {

    }

    @Override
    public void persistAndFlush(ItemAttribute entity) {

    }

    @Override
    public void flush() {

    }
}
