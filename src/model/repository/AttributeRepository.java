package model.repository;

import model.entity.Attribute;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ivoni on 1/13/2017.
 */
public class AttributeRepository extends AbstractRepository<Attribute> {

    public AttributeRepository(Connection connection) {
        super(connection);
    }

    @Override
    public Attribute getById(int id) {
        Attribute attribute = null;
        try {
            ResultSet rs = querySelector.getResultSet("SELECT * FROM attribute WHERE id = " + id);
            attribute = mapAttribute(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attribute;
    }

    private Attribute mapAttribute(ResultSet rs) throws SQLException {
        Attribute attribute = null;
        if (rs.next()) {
            attribute = new Attribute();
            attribute.setId(rs.getInt("id"));
            attribute.setName(rs.getString("name"));
            identityMap.add(attribute);
        }
        return attribute;
    }

    @Override
    public Collection<Attribute> findAll() {
        ArrayList<Attribute> attributes = new ArrayList<Attribute>();
        try {
            ResultSet rs = querySelector.getResultSet("SELECT * FROM attribute");
            Attribute attribute = mapAttribute(rs);
            while (attribute != null) {
                attributes.add(attribute);
                attribute = mapAttribute(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attributes;
    }

    @Override
    public Collection<Attribute> findBy(String[] conditions, Object[] attributes) {
        return null;
    }

    @Override
    public void persist(Attribute entity) {

    }

    @Override
    public void persistAndFlush(Attribute entity) {

    }

    @Override
    public void flush() {

    }
}
