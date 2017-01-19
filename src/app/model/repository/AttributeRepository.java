package app.model.repository;

import app.model.Model;
import app.model.entity.Attribute;
import hyggedb.select.Condition;
import hyggedb.select.Selection;

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
            selection.where("id=?", id);
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
        ResultSet rs = model.getSelectionExecutor().getResult(new Selection("attribute"));
        return mapAttributes(rs);
    }

    private Collection<Attribute> mapAttributes(ResultSet rs) {
        Collection<Attribute> attributes = new ArrayList<>();
        try {
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

    public Collection<Attribute> findByCategory(int categoryId) {
        Selection selection = new Selection("category", "");
        selection.where("id=?",categoryId);
        selection.join("category_attribute", "id", "category_id")
                .join("attribute", "attribute_id", "id")
                .addColumns("*");
        ResultSet rs = model.getSelectionExecutor().getResult(selection);
        return mapAttributes(rs);
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
