package model.repository;

import model.Connector;
import model.SqlIO;
import model.entity.Entity;

import model.entity.ItemAttribute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ivoni on 1/15/2017.
 */
public class ItemAttribteRepository implements Repository{
    private SqlIO sqlIO;

    public ItemAttribteRepository (Connector connector) {
        sqlIO = new SqlIO(connector.getConnection());
    }

    @Override
    public Entity getById(int id) {
        ItemAttribute itemRep = null;

        try {
            ResultSet rs = sqlIO.getResultSet("SELECT * FROM itemAtribute WHERE id = " + id);
            if (rs.next()) {

                itemRep.setId(rs.getInt("id"));
                itemRep.setItemId(rs.getInt("user_id"));
                itemRep.setItemId(rs.getInt("attribute_id"));
                itemRep.setData(rs.getString("data"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemRep;
    }

    @Override
    public Collection<ItemAttribute> findAll() {
        ArrayList<ItemAttribute> itemAttributes = new ArrayList<ItemAttribute>();

        try {
            ResultSet rs = sqlIO.getResultSet("SELECT * FROM itemAtribute");
            while (rs.next()) {

                int user_id = rs.getInt("id");
                int item_id = rs.getInt("user_id");
                int attribute_id = rs.getInt("attribute_id");
                String data = rs.getString("data");
                itemAttributes.add(new ItemAttribute(user_id, attribute_id, item_id, data));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemAttributes;

    }
}
