package model.repository;

import model.entity.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ivoni on 1/14/2017.
 */
public class CategoryRepository extends AbstractRepository<Category> {

    public CategoryRepository(Connection connection) {
        super(connection);
    }

    @Override
    public Category getById(int id) {
        Category category = null;
        try {
            ResultSet rs = querySelector.getResultSet("SELECT * FROM category WHERE id = " + id);
            category = mapCategory(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    private Category mapCategory(ResultSet rs) throws SQLException {
        Category category = null;
        if (rs.next()) {
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            identityMap.add(category);
        }
        return category;
    }

    @Override
    public Collection<Category> findAll() {
        ArrayList<Category> categories = new ArrayList<Category>();
        try {
            ResultSet rs = querySelector.getResultSet("SELECT * FROM category WHERE");
            Category category = mapCategory(rs);
            while (category != null) {
                categories.add(category);
                category = mapCategory(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Collection<Category> findBy(String[] conditions, Object[] attributes) {
        return null;
    }

    @Override
    public void persist(Category entity) {

    }

    @Override
    public void persistAndFlush(Category entity) {

    }

    @Override
    public void flush() {

    }
}

