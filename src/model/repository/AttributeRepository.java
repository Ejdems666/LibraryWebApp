package model.repository;

import model.Connector;
import model.QuerySelector;
import model.entity.Attribute;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ivoni on 1/13/2017.
 */
public class AttributeRepository implements Repository<Attribute>{

    private QuerySelector querySelector;

    public AttributeRepository(Connector connector) {
        querySelector = new QuerySelector(connector.getConnection());
    }

    @Override
    public Attribute getById(int id) {

        Attribute attribute = null;

        try {
            ResultSet rs = querySelector.getResultSet("SELECT * FROM attribute WHERE id = " + id);
            if (rs.next()) {
                attribute.setName(rs.getString("name"));
                attribute.setId(rs.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attribute;
    }

        @Override
    public Collection<Attribute> findAll() {

            ArrayList<Attribute> attributes = new ArrayList<Attribute>();

            try {
                ResultSet rs = querySelector.getResultSet("SELECT * FROM attribute");
                while(rs.next())
                {
                    String name = rs.getString("name");
                    int user_id = rs.getInt("user_id");
                    attributes.add(new Attribute(name, user_id));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return attributes;
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
