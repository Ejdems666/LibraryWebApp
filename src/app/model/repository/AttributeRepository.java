package app.model.repository;

import hyggedb.select.Condition;
import hyggedb.select.Selection;
import app.model.Model;
import app.model.entity.Attribute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ivoni on 1/13/2017.
 */
public class AttributeRepository extends AbstractRepository<Attribute> {

    public AttributeRepository(Model model) {
        super(model);
    }

    @Override
    public Attribute getById(int id) {
        Attribute attribute = null;
        try {
            Selection selection = new Selection("attribute");
            selection.where("id=?",id);
            ResultSet rs = model.getSelectionExecutor().getResult(selection);
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
            ResultSet rs = model.getSelectionExecutor().getResult(new Selection("attribute"));
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
    public Collection<Attribute> findBy(Condition condition) {
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
