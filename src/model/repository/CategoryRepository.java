package model.repository;

import model.Connector;
import model.SqlIO;
import model.entity.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ivoni on 1/14/2017.
 */
public class CategoryRepository implements Repository<Category> {

    private SqlIO sqlIO;

    public CategoryRepository(Connector connector) {
        sqlIO = new SqlIO(connector.getConnection());
    }

    @Override
    public Category getById(int id) {
        Category category = null;
        try {
            ResultSet rs = sqlIO.getResultSet("SELECT * FROM category WHERE id = " + id);
            if (rs.next()) {
                category.setName(rs.getString("name"));
                category.setId(rs.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    @Override
    public Category[] findAll() {

            ArrayList<Category> categories = new ArrayList<Category>();
            try {
                ResultSet rs = sqlIO.getResultSet("SELECT * FROM category WHERE");
                while (rs.next()) {
                    String name = rs.getString("name");
                    int user_id = Integer.parseInt(rs.getString("user_id"));
                    categories.add(new Category(name, user_id));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Category[] categoryGeneric = new Category[categories.size()];
            return categoryGeneric;
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

